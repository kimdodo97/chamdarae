package com.chamdarae.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Result;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class User {

    private Long id;
    private String userEmail;
    private String userLoginId;
    private String userName;
    private String userNickName;
    private String userGender;
    private String userPhone;
    private LocalDateTime user_birthdate;

    @Builder
    public User(String userEmail, String userLoginId, String userName, String userNickName, String userGender, String userPhone, LocalDateTime user_birthdate) {
        this.userEmail = userEmail;
        this.userLoginId = userLoginId;
        this.userName = userName;
        this.userNickName = userNickName;
        this.userGender = userGender;
        this.userPhone = userPhone;
        this.user_birthdate = user_birthdate;
    }
}
