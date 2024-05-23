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
    private String memberEmail;

    @Column(unique = true)
    private String memberLoginId;

    private String memberPassWord;

    private String memberName;

    @Column(unique = true)
    private String memberNickname;

    private Gender memberGender;

    private String memberPhone;

    private LocalDate memberBirthDate;
    @Embedded
    private Address memberAddress;

    @OneToMany(mappedBy = "member")
    private List<Recipe> recipes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Member(String memberEmail, String memberLoginId, String memberPassWord, String memberName, String memberNickname, Gender memberGender, String memberPhone, LocalDate memberBirthDate, Address memberAddress, List<Recipe> recipes, List<Comment> comments) {
        this.memberEmail = memberEmail;
        this.memberLoginId = memberLoginId;
        this.memberPassWord = memberPassWord;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberGender = memberGender;
        this.memberPhone = memberPhone;
        this.memberBirthDate = memberBirthDate;
        this.memberAddress = memberAddress;
        this.recipes = recipes;
        this.comments = comments;
    }

    public MemberEditor.MemberEditorBuilder toEditor(){
        return MemberEditor.builder()
                .name(memberName)
                .email(memberEmail)
                .gender(memberGender)
                .nickname(memberNickname)
                .phone(memberPhone)
                .password(memberPassWord)
                .birthDate(memberBirthDate)
                .address(memberAddress);
    }

    public void edit(MemberEditor memberEditor){
        memberName = memberEditor.getName();
        memberPassWord = memberEditor.getPassword();
        memberEmail  = memberEditor.getEmail();
        memberGender = memberEditor.getGender();
        memberNickname = memberEditor.getNickname();
        memberPhone = memberEditor.getPhone();
        memberBirthDate = memberEditor.getBirthDate();
        memberAddress = memberEditor.getAddress();
    }
}
