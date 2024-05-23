package com.chamdarae.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.chamdarae.domain.Address;
import com.chamdarae.domain.Gender;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberCreate {
    @NotBlank(message="이름 필수")
    private String name;
    
    @NotNull(message = "성별 필수")
    private Gender gender;

    @NotBlank(message = "이메일 필수")
    private String email;

    @NotBlank(message = "아이디 필수")
    private String loginId;
    
    @NotBlank(message = "비밀번호 필수")
    private String password;
    
    @NotBlank(message = "닉네임 필수")
    private String nickname;

    private String phone;
    
    @NotNull(message = "생년월일 필수")
    private LocalDate birthDate;

    private Address address;

    @Builder
    public MemberCreate(String name, Gender gender, String email, String loginId, String password, String nickname,
                        String phone, LocalDate birthDate,String city, String street, String zipcode) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = Address.builder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build();
    }

    public void validation(){

    }
}
