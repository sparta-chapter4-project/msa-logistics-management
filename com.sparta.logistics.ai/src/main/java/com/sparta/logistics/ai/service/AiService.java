package com.sparta.logistics.ai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.logistics.ai.dto.GeminiResponseDto;
import com.sparta.logistics.ai.dto.HubResponseDto;
import com.sparta.logistics.ai.dto.SlackRequestDto;
import com.sparta.logistics.ai.entity.Ai;
import com.sparta.logistics.ai.repository.AiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AiService {

    private static final double RE = 6371.00877; // 지구 반경(km)
    private static final double GRID = 5.0; // 격자 간격(km)
    private static final double SLAT1 = 30.0; // 투영 위도 1 (degree)
    private static final double SLAT2 = 60.0; // 투영 위도 2 (degree)
    private static final double OLON = 126.0; // 기준점 경도 (degree)
    private static final double OLAT = 38.0; // 기준점 위도 (degree)
    private static final double XO = 43; // 기준점 X좌표 (GRID)
    private static final double YO = 136; // 기준점 Y좌표 (GRID)

    private final RestTemplate restTemplate;
    private final AiRepository aiRepository;

    private final DeliveryManagerService deliveryManagerService;
    private final HubService hubService;
    private final SlackService slackService;
    private final OrderService orderService;
    private final DeliveryService deliveryService;

    private final String WEATHER_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0";
    @Value("${gemini-key}")
    private String apiKey;

    @Transactional
    public String create(Long userId, String question) {
        String answer = geminiApi(question);

        aiRepository.save(Ai.create(userId, question, answer));

        return answer;
    }

    @Transactional
    public String geminiApi(String question) {
        String uri = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent";

        Map<String, Object> textMap = new HashMap<>();
        textMap.put("text", question);

        Map<String, Object> partsMap = new HashMap<>();
        partsMap.put("parts", List.of(textMap));

        Map<String, Object> response = new HashMap<>();
        response.put("contents", List.of(partsMap));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("x-goog-api-key", apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(response, headers);
        ResponseEntity<GeminiResponseDto> geminiResponse = restTemplate.exchange(uri, HttpMethod.POST, entity, GeminiResponseDto.class);

        return geminiResponse.getBody().getCandidates().get(0).getContent().getParts().get(0).getText();
    }

    @Transactional
    @Scheduled(cron = "0 0 6 * * ?")
    public void companyDeliveryManagerSlack() {
        deliveryManagerService.getDeliveryManagerListByType("MASTER").stream().forEach(deliveryManager -> {
            HubResponseDto.Get hubAddress = hubService.getHubAddress(deliveryManager.getHubId());
            log.info(String.valueOf(hubAddress.getLongitude()));
            log.info(String.valueOf(hubAddress.getLatitude()));

            int[] address = convertCoordinates(hubAddress.getLatitude(), hubAddress.getLongitude());


            String key = "oDyJC78dKPXvcz7QZIPMS8cTS8s77B5n5vPwipLSd5hBMBg2x7j6VT3ztsZXO2PuW4MDmnA/q32Z5MHf7OYQsw==";

            String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=" + key + "&pageNo=1&numOfRows=1000&dataType=JSON&base_date=" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "&base_time=0600&nx=" + address[0] + "&ny=" + address[1];

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                float temp = 0;
                float reh = 0;
                int pty = 0;
                String deliveryList = deliveryService.getDeliveryListByDeliveryManagerId(deliveryManager.getDeliveryManagerId()).stream().map(delivery -> "( 주문ID: " + delivery.getOrderId() + ", 주소: " + delivery.getAddress() + ", 상태: " + delivery.getStatus() + ", 상품ID: " + delivery.getRecipientId() + ")")
                    .collect(Collectors.joining(", "));

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = null;

                try {
                    rootNode = objectMapper.readTree(response.getBody());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                // "item" 배열의 데이터를 가져오기
                JsonNode items = rootNode.path("response").path("body").path("items").path("item");

                // "WSD" 항목을 찾고, 해당 obsrValue 값을 반환
                for (JsonNode item : items) {
                    String category = item.path("category").asText();
                    switch (category) {
                        case "T1H":
                            temp = Float.parseFloat(item.path("obsrValue").asText());
                            break;
                        case "REH":
                            reh = Float.parseFloat(item.path("obsrValue").asText());
                            break;
                        case "PTY":
                            pty = Integer.parseInt(item.path("obsrValue").asText());
                            break;
                        default:
                            break;
                    }
                }
                String weather = "";

                if (pty == 0) {
                    weather = "맑음";
                }
                if (pty == 1) {
                    weather = "비";
                }
                if (pty == 2) {
                    weather = "눈/비";
                }
                if (pty == 3) {
                    weather = "눈";
                }
                if (pty == 5) {
                    weather = "빗방울";
                }
                if (pty == 6) {
                    weather = "진눈깨비";
                }
                if (pty == 7) {
                    weather = "눈";
                }

                slackService.sendDeliveryManager(SlackRequestDto.Create.of(deliveryManager.getSlackId(), create(deliveryManager.getUserId(), "오늘 날씨는 " + weather + " 기온은 " + temp + " 습도는 " + reh + ", 오늘 배송은 " + deliveryList + " 이것을 요약하여 정라해줘")));
            }
        });
        //배송 담당자 테이블에서 타입이 업체 배송 담당자인 것들의 리스트를 가져온다.

        //맵을 돌려 배송 담당자 id와 허브 id를 가져온다.

        //허브 id를 통해 허브의 위도 경도를 가져온다.

        //해당 위도 경도로 날씨 정보를 가져온다.

        //배송 테이블에서 배송 담당자 id가 일치하는 것들의 리스트를 가져온다.

        //두 데이터를 합쳐 ai를 통해 정보를 요약하고 슬랙 메세지를 보낸다.
    }

    @Transactional
    @Scheduled(cron = "0 0 8 * * ?")
    public void hubDeliveryManagerSlack() {
        deliveryManagerService.getDeliveryManagerListByType("HUBDELIVERYMANAGER").stream().forEach(deliveryManager -> {
            UUID deliveryManagerId = deliveryManager.getDeliveryManagerId();

            String orderList = orderService.getOneDay().stream().map(order -> "( 요청업체ID: " + order.getDemandCompanyId() + ", 수령업체ID: " + order.getSupplyCompanyId() + ", 상품ID: " + order.getProductId() + ", 갯수: " + order.getAmount() + ", 상태: " + order.getStatus() + ")")
                .collect(Collectors.joining(", "));

            slackService.sendDeliveryManager(SlackRequestDto.Create.of(deliveryManager.getSlackId(), create(deliveryManager.getUserId(), orderList + "이것을 요약하여 정라해줘")));
        });
        //배송 담당자 테이블에서 타입이 허브 이동 담당자인 것들의 리스트를 가져온다. (배송담당자 id, 슬랙아이디)

        //24시간 내의 주문을 가져온다. 그런데 조건이 있어야됨. 배송 담당자가 담당하고 있는 주문에 대해서 가져와야 할듯?? 이야기 해봐야겠다. (요청업체, 수령업체, 상품, 주문 샅애)

        //해당 데이터를 ai를 통해 요약하고 슬랙 메세지를 보낸다.
    }

    private int[] convertCoordinates(float latitude, float longitude) {
        double DEGRAD = Math.PI / 180.0;
        double RADDEG = 180.0 / Math.PI;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon = OLON * DEGRAD;
        double olat = OLAT * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);

        double ra = Math.tan(Math.PI * 0.25 + latitude * DEGRAD * 0.5);
        ra = re * sf / Math.pow(ra, sn);
        double theta = longitude * DEGRAD - olon;
        if (theta > Math.PI) theta -= 2.0 * Math.PI;
        if (theta < -Math.PI) theta += 2.0 * Math.PI;
        theta *= sn;

        int x = (int) Math.floor(ra * Math.sin(theta) + XO + 0.5);
        int y = (int) Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);

        return new int[]{x, y};
    }
}