package com.sparta.logistics.user.service;

import com.sparta.logistics.user.dto.UserDto;
import com.sparta.logistics.user.dto.UserRequestDto;
import com.sparta.logistics.user.entity.User;
import com.sparta.logistics.user.entity.UserRoleEnum;
import com.sparta.logistics.user.global.security.UserDetailsImpl;
import com.sparta.logistics.user.global.security.UserDetailsServiceImpl;
import com.sparta.logistics.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;

    private final UserDetailsServiceImpl userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
//    private final JwtUtil jwtUtil;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    @Value("${service.jwt.secret-key}")
    private String jwtSecretKey;
    private SecretKey key;
    public static final String AUTHORIZATION_KEY = "role";
    public int ACCESS_TOKEN_TTL_SECONDS = 60 * 60 * 1000;

    @PostConstruct
    public void init() {
        byte[] bytes = Decoders.BASE64URL.decode(jwtSecretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    @Transactional
    public void signUp(UserRequestDto.SignUpReqDto signUpReqDto) {
        if (userRepository.findByName(signUpReqDto.getName()).isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        if (userRepository.findByEmail(signUpReqDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        userRepository.save(User.create(signUpReqDto, passwordEncoder.encode(signUpReqDto.getPassword())));
    }

//    @Transactional
    public String signIn(UserRequestDto.SignInReqDto signInReqDto) {
        User user = userRepository.findByName(signInReqDto.getName()).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        if (!passwordEncoder.matches(signInReqDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        log.info("뭐가문제야");

//        TestDto testDto = new TestDto(signInReqDto.getName(),signInReqDto.getPassword());
//
        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getAuthority()));

        System.out.println("signInReqDto.getName() = " + signInReqDto.getName());

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(signInReqDto.getName(), signInReqDto.getPassword())
        );

//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInReqDto.getName(), signInReqDto.getPassword(), authorities);


        log.info("인증이 안되나");
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UserDetailsImpl userDetails = this.userDetailsService.loadUserByUsername(user.getName());
//        User user2 = (User) authentication.getPrincipal();

        log.info("어디까지오는겨");

        var userDto = UserDto.create(user);

        log.info("여기까진오나");

        redisService.setValue("user:" + signInReqDto.getName(), userDto);

        log.info("어디까지오나");

//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(authenticationToken);

        return createToken(userDetails.getUsername(), userDetails.getRole());
    }

    public String createToken(String name, UserRoleEnum role) {
        return "Bearer " +
            Jwts.builder()
                .subject(name)
                .claim(AUTHORIZATION_KEY, role)
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_TTL_SECONDS))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public Boolean verifyUser(Long userId) {
        return userRepository.findById(userId).isPresent();
    }

//    private record UserDto(String userName, String role) {
//        public static UserDto fromUser(org.springframework.security.core.userdetails.User user) {
//            return new UserDto(
//                user.getUsername(),
//                user.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.toList())
//            );
//        }
//    }
    private record TestDto(String username, String password){}
}