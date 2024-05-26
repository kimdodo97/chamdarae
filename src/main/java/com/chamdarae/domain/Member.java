package com.chamdarae.domain;

import com.chamdarae.domain.editor.MemberEditor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String loginId;

    private String password;

    private String name;

    @Column(unique = true)
    private String nickname;

    private Gender gender;

    private String phone;

    private LocalDate birthDate;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Recipe> recipes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Member(String email, String loginId, String password, String name, String nickname, Gender gender, String phone, LocalDate birthDate, Address address, List<Recipe> recipes, List<Comment> comments) {
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
        this.recipes = recipes;
        this.comments = comments;
    }

    public MemberEditor.MemberEditorBuilder toEditor(){
        return MemberEditor.builder()
                .name(name)
                .email(email)
                .gender(gender)
                .nickname(nickname)
                .phone(phone)
                .password(password)
                .birthDate(birthDate)
                .address(address);
    }

    public void edit(MemberEditor memberEditor){
        name = memberEditor.getName();
        password = memberEditor.getPassword();
        email  = memberEditor.getEmail();
        gender = memberEditor.getGender();
        nickname = memberEditor.getNickname();
        phone = memberEditor.getPhone();
        birthDate = memberEditor.getBirthDate();
        address = memberEditor.getAddress();
    }
}
