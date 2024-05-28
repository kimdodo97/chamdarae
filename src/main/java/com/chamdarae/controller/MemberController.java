package com.chamdarae.controller;

import javax.validation.Valid;

import com.chamdarae.request.MemberCreate;
import com.chamdarae.request.MemberEdit;
import com.chamdarae.response.MemberResponse;
import com.chamdarae.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

//    @GetMapping( "/member/login")
//    public String loginForm() {
//        System.out.println("login");
//        return "view/login";
//    }
//
//    @GetMapping("/member/join")
//    public String joinForm(){
//        System.out.println("join");
//        return "view/join";
//    }

    @PostMapping("/member")
    public void join(@RequestBody @Valid MemberCreate request){
        Long joinId = memberService.join(request);
    }

    @GetMapping("/member/{memberId}")
    public MemberResponse get(@PathVariable Long memberId){
        return memberService.get(memberId);
    }

    @PatchMapping("/member/{memberId}")
    public MemberResponse edit(@PathVariable Long memberId, @RequestBody @Valid MemberEdit request){
        return memberService.edit(memberId,request);
    }

    @DeleteMapping("/member/{memberId}")
    public void quit(@PathVariable Long memberId){
        memberService.quit(memberId);
    }

    @GetMapping("/member-email/{email}")
    public boolean duplicateEmail(@PathVariable String email){
        return memberService.duplicateEmail(email);
    }

    @GetMapping("/member-nickname/{nickname}")
    public boolean duplicateNickname(@PathVariable String nickname){
        return memberService.duplicateNickname(nickname);
    }

    @GetMapping("/member-login-id/{loginId}")
    public boolean duplicateLoginId(@PathVariable String loginId){
        return memberService.duplicateLoginId(loginId);
    }
}