package com.chamdarae.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.chamdarae.domain.Address;
import com.chamdarae.domain.Gender;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberEdit {
    @NotBlank(message="이름 필수")
    private String name;

    @NotNull(message = "성별 필수")
    private Gender gender;

    @NotBlank(message = "이메일 필수")
    private String email;

    @NotBlank(message = "비밀번호 필수")
    private String password;

    @NotBlank(message = "닉네임 필수")
    private String nickname;

    private String phone;

    @NotNull(message = "성별 필수")
    private LocalDate birthDate;

    private Address address;

    @Builder
    public MemberEdit(String name, Gender gender, String email,
                      String password, String nickname, String phone, LocalDate birthDate, String city,
                      String street,String zipcode) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = new Address(city, street, zipcode);
    }
}
