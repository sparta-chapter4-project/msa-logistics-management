package com.sparta.logistics.user.entity;

import com.sparta.logistics.user.dto.SignUpReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "p_user")
public class User {

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

    public User(SignUpReqDto signUpReqDto, String password) {
        this.name = signUpReqDto.getName();
        this.password = password;
        this.email = signUpReqDto.getEmail();
        this.tel = signUpReqDto.getTel();
        this.role = signUpReqDto.getRole();
    }
}
