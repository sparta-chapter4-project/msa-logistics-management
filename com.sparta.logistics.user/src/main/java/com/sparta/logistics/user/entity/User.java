package com.sparta.logistics.user.entity;

import com.sparta.logistics.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "p_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Column(nullable = false, unique = true)
    private String tel;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public static User create(UserRequestDto.SignUpReqDto signUpReqDto, String password) {
        return User.builder()
            .name(signUpReqDto.getName())
            .password(password)
            .email(signUpReqDto.getEmail())
            .tel(signUpReqDto.getTel())
            .role(signUpReqDto.getRole())
            .build();
    }
}
