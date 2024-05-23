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
                .memberName(memberCreate.getName())
                .memberGender(memberCreate.getGender())
                .memberEmail(memberCreate.getEmail())
                .memberLoginId(memberCreate.getLoginId())
                .memberPassWord(memberCreate.getPassword())
                .memberNickname(memberCreate.getNickname())
                .memberPhone(memberCreate.getPhone())
                .memberBirthDate(memberCreate.getBirthDate())
                .memberAddress(memberCreate.getAddress())
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
        return memberRepository.existsByMemberEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean duplicateNickname(String nickname){
        return memberRepository.existsByMemberNickname(nickname);
    }

    @Transactional(readOnly = true)
    public boolean duplicateLoginId(String loginId){
        return memberRepository.existsByMemberLoginId(loginId);
    }
}
