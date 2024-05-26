package com.chamdarae.service;

import com.chamdarae.domain.Member;
import com.chamdarae.domain.editor.MemberEditor;
import com.chamdarae.exception.MemberNotFound;
import com.chamdarae.repository.MemberRepository;
import com.chamdarae.request.MemberCreate;
import com.chamdarae.request.MemberEdit;
import com.chamdarae.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(MemberCreate memberCreate){
        Member member = Member.builder()
                .name(memberCreate.getName())
                .gender(memberCreate.getGender())
                .email(memberCreate.getEmail())
                .loginId(memberCreate.getLoginId())
                .password(memberCreate.getPassword())
                .nickname(memberCreate.getNickname())
                .phone(memberCreate.getPhone())
                .birthDate(memberCreate.getBirthDate())
                .address(memberCreate.getAddress())
                .build();

        memberRepository.save(member);
        return member.getId();
    }

    @Transactional(readOnly = true)
    public MemberResponse get(Long id){
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFound::new);
        return new MemberResponse(member);
    }

    public MemberResponse edit(Long id, MemberEdit memberEdit){
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFound::new);
        MemberEditor.MemberEditorBuilder memberEditorBuilder = member.toEditor();

        MemberEditor memberEditor = memberEditorBuilder
                .name(memberEdit.getName())
                .password(memberEdit.getPassword())
                .email(memberEdit.getEmail())
                .gender(memberEdit.getGender())
                .nickname(memberEdit.getNickname())
                .phone(memberEdit.getPhone())
                .birthDate(memberEdit.getBirthDate())
                .address(memberEdit.getAddress())
                .build();

        member.edit(memberEditor);
        return new MemberResponse(member);
    }

    public void quit(Long id){
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFound::new);
        memberRepository.delete(member);
    }

    @Transactional(readOnly = true)
    public boolean duplicateEmail(String email){
        return memberRepository.existsByemail(email);
    }

    @Transactional(readOnly = true)
    public boolean duplicateNickname(String nickname){
        return memberRepository.existsBynickname(nickname);
    }

    @Transactional(readOnly = true)
    public boolean duplicateLoginId(String loginId){
        return memberRepository.existsByloginId(loginId);
    }
}
