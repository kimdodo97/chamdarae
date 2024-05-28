package com.chamdarae.service;

import javax.validation.*;

import com.chamdarae.domain.Address;
import com.chamdarae.domain.Gender;
import com.chamdarae.domain.Member;
import com.chamdarae.exception.MemberNotFound;
import com.chamdarae.repository.MemberRepository;
import com.chamdarae.request.MemberCreate;
import com.chamdarae.request.MemberEdit;
import com.chamdarae.response.MemberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeEach
    public void clear(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 가입 테스트")
    void test1() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(1997,11,4);
        MemberCreate memberCreate = MemberCreate.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .password("dodo")
                .phone("010-3344-4954")
                .email("eh4536@naver.com")
                .birthDate(birthDate)
                .nickname("kimdodo")
                .mainAddr("부산")
                .detailAddr("전포대로")
                .zipcode("1234")
                .build();

        //when
        Long memberId = memberService.join(memberCreate);
        Member findMember = memberRepository.findById(memberId).get();

        //then
        assertEquals(findMember.getLoginId(),memberCreate.getLoginId());
        assertEquals(findMember.getPassword(),memberCreate.getPassword());
    }
    
    @Test
    @DisplayName("동일한 아이디 에러")
    void test2() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(1997,11,4);
        MemberCreate memberCreate1 = MemberCreate.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .loginId("dodo")
                .phone("010-3344-4954")
                .email("eh4536@naver.com")
                .birthDate(birthDate)
                .nickname("kimdodo")
                .mainAddr("부산")
                .detailAddr("전포대로")
                .zipcode("1234")
                .build();

        memberService.join(memberCreate1);

        MemberCreate memberCreate2 = MemberCreate.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .loginId("dodo")
                .phone("010-3344-4954")
                .email("eh4536@naver.com")
                .birthDate(birthDate)
                .nickname("kimdodo")
                .mainAddr("부산")
                .detailAddr("전포대로")
                .zipcode("1234")
                .build();
        //when
        assertThrows(Exception.class,()-> memberService.join(memberCreate2));
        //then
        
    }
    @Test
    @DisplayName("사용자 조회 테스트")
    void test3() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(1997,11,4);
        Member member = Member.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .password("dodo123")
                .build();
        memberRepository.save(member);
        //when
        MemberResponse findMember = memberService.get(member.getId());
        //then
        assertNotNull(findMember);
    }
    
    @Test
    @DisplayName("조회 실패 테스트")
    void test4() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(1997,11,4);
        Member member = Member.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .password("dodo123")
                .build();
        memberRepository.save(member);
        //expect
        assertThrows(MemberNotFound.class,()-> memberService.get(member.getId()+1));
    }
    
    @Test
    @DisplayName("사용자 정보 수정 테스트")
    void test5() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(1997,11,4);
        Address address = new Address("부산","남구 전포대로","123");

        String changedNickname = "dodokim";
        Member member = Member.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .password("dodo")
                .phone("010-3344-4954")
                .email("eh4536@naver.com")
                .birthDate(birthDate)
                .nickname("kimdodo")
                .address(address)
                .build();

        memberRepository.save(member);

        MemberEdit memberEdit = MemberEdit.builder()
                .nickname(changedNickname).build();
        //when
        memberService.edit(member.getId(),memberEdit);
        //then
        Member changedMember = memberRepository.findById(member.getId())
                .orElseThrow(MemberNotFound::new);

        assertEquals(changedNickname,changedMember.getNickname());
    }

    @Test
    @DisplayName("사용자 탈퇴 테스트")
    void test6() throws Exception {
        //given
        Member member = Member.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .password("dodo")
                .phone("010-3344-4954")
                .email("eh4536@naver.com")
                .nickname("kimdodo")
                .build();

        memberRepository.save(member);
        //when
        memberService.quit(member.getId());
        //then
        assertEquals(0,memberRepository.count());
    }

    @Test
    @DisplayName("중복 확인 테스트")
    void test7() throws Exception {
        //given
        Member member = Member.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .password("dodo")
                .phone("010-3344-4954")
                .email("eh4536@naver.com")
                .nickname("kimdodo")
                .build();

        memberRepository.save(member);
        //when
        String email = "eh4536@naver.com";
        String nickname = "kimdodo";
        String loginId = "ehdud326";
        //then
        assertTrue(memberService.duplicateEmail(email));
        assertTrue(memberService.duplicateLoginId(loginId));
        assertTrue(memberService.duplicateNickname(nickname));
    }

    @Test
    @DisplayName("사용자 정보 수정시 null 테스트")
    void test8() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(1997,11,4);
        Address address = new Address("부산","남구 전포대로","123");

        String changedNickname = "dodokim";
        Member member = Member.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .password("dodo")
                .phone("010-3344-4954")
                .email("eh4536@naver.com")
                .birthDate(birthDate)
                .nickname("kimdodo")
                .address(address)
                .build();

        memberRepository.save(member);

        MemberEdit memberEdit = MemberEdit.builder()
                .nickname(changedNickname)
                .password(null)
                .build();
        //when
        memberService.edit(member.getId(),memberEdit);
        //then
        Member changedMember = memberRepository.findById(member.getId())
                .orElseThrow(MemberNotFound::new);

        assertEquals(changedNickname,changedMember.getNickname());
        assertEquals("dodo",changedMember.getPassword());
    }
}