package com.chamdarae.response;

import com.chamdarae.domain.Address;
import com.chamdarae.domain.Gender;
import com.chamdarae.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberResponse {
    private Long id;
    private String email;
    private String loginId;
    private String password;
    private String name;
    private String nickname;
    private Gender gender;
    private String phone;
    private LocalDate birth;
    private Address address;

    public MemberResponse(Member member) {
        id = member.getId();
        email = member.getEmail();
        loginId = member.getLoginId();
        password = member.getPassword();
        name = member.getName();
        nickname = member.getNickname();
        gender = member.getGender();
        phone = member.getPhone();
        birth = member.getBirthDate();
        address = member.getAddress();
    }

    @Builder
    public MemberResponse(Long id, String email, String loginId, String password, String name,
                          String nickname, Gender gender, String phone, LocalDate birth, Address address) {
        this.id = id;
        this.email = email;
        this.loginId = loginId;
        this.loginId = loginId;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
    }
}
