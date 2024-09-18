package com.sparta.logistics.ai.service;

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

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AiService {

    private final RestTemplate restTemplate;
    private final AiRepository aiRepository;

    private final DeliveryManagerService deliveryManagerService;
    private final HubService hubService;
    private final SlackService slackService;
    private final OrderService orderService;

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
        deliveryManagerService.getDeliveryManagerListByType("COMPANYDELIVERYMANAGER").stream().forEach(deliveryManager -> {
            UUID deliveryManagerId = deliveryManager.getDeliveryManagerId();
            HubResponseDto.Get hubAddress = hubService.getHubAddress(deliveryManager.getHubId());
            log.info(String.valueOf(hubAddress.getLongitude()));
            log.info(String.valueOf(hubAddress.getLatitude()));

            slackService.sendDeliveryManager(SlackRequestDto.Create.of(deliveryManager.getSlackId(), create(deliveryManager.getUserId(), "오늘 날씨는 맑음, 오늘 배송은 경기도 안양에 우유1개, 평택에 초콜릿1개, 용인에 사탕5개를 해야한다. 이것을 요약하여 정라해줘")));
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

            slackService.sendDeliveryManager(SlackRequestDto.Create.of(deliveryManager.getSlackId(), create(deliveryManager.getUserId(), orderList+"이것을 요약하여 정라해줘")));
        });
        //배송 담당자 테이블에서 타입이 허브 이동 담당자인 것들의 리스트를 가져온다. (배송담당자 id, 슬랙아이디)

        //24시간 내의 주문을 가져온다. 그런데 조건이 있어야됨. 배송 담당자가 담당하고 있는 주문에 대해서 가져와야 할듯?? 이야기 해봐야겠다. (요청업체, 수령업체, 상품, 주문 샅애)

        //해당 데이터를 ai를 통해 요약하고 슬랙 메세지를 보낸다.
    }

//    @Scheduled(cron = "0 0 6 * * ?")
//    public void weatherDelivery() {
//
//        JSONObject json = new JSONObject();
//
//        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=%2BdFN4iatEHVtyqfHZvcflDjBGedrbofm1XfJOtak0rnTMi7f46ilQ95rxKfkfmAfcq2YCHq1qCnxLgNuyV6iKA%3D%3D&pageNo=1&numOfRows=1000&dataType=JSON&base_date=20240913&base_time=0600&nx=55&ny=127";
//
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            // 응답 데이터 반환
//            return ResponseEntity.ok(response.getBody());
//        } else {
//            throw new RuntimeException("API 호출 실패: " + response.getStatusCode());
//        }
//
////        String answer = geminiApi("오늘 날씨는 어떄?");
////        log.info(answer);
//
//    }
}