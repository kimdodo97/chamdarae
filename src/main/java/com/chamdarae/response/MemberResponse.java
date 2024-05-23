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
        email = member.getMemberEmail();
        loginId = member.getMemberLoginId();
        password = member.getMemberPassWord();
        name = member.getMemberName();
        nickname = member.getMemberNickname();
        gender = member.getMemberGender();
        phone = member.getMemberPhone();
        birth = member.getMemberBirthDate();
        address = member.getMemberAddress();
    }

    @Builder
    public MemberResponse(Long id, String email, String loginId, String password, String name,
                          String nickname, Gender gender, String phone, LocalDate birth, Address address) {
        this.id = id;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
    }
}
