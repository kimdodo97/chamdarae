package com.chamdarae.controller;

import com.chamdarae.domain.Gender;
import com.chamdarae.domain.Member;
import com.chamdarae.repository.MemberRepository;
import com.chamdarae.request.MemberCreate;
import com.chamdarae.request.MemberEdit;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("POST /member 요청(회원 가입) 테스트")
    void test1() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(1997,11,4);
        MemberCreate request = MemberCreate.builder()
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

        String json = objectMapper.writeValueAsString(request);
        //when
        mockMvc.perform(post("/member")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        assertEquals(1L,memberRepository.count());
        Member findedMember = memberRepository.findAll().get(0);
        assertEquals(findedMember.getLoginId(),request.getLoginId());
        assertEquals(findedMember.getPassword(),request.getPassword());
        assertEquals(findedMember.getEmail(),request.getEmail());
    }

    @Test
    @DisplayName("GET /member/{memberId}(단순 조회) 요청시 ")
    void test2() throws Exception {
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
        //expect
        mockMvc.perform(get("/member/{memberId}",member.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(member.getId()))
                .andExpect(jsonPath("$.loginId").value(member.getLoginId()))
                .andDo(print());

    }

    @Test
    @DisplayName("PATCH /member/{memberId} 요청(수정) 테스트")
    void test3() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(1997,11,4);
        Member member = Member.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .password("dodo")
                .phone("010-3344-4954")
                .email("eh4536@naver.com")
                .nickname("kimdodo")
                .birthDate(birthDate)
                .build();

        memberRepository.save(member);

        String otherPassword = "dododo";
        String otherNickname = "dodokim";

        MemberEdit memberEdit = MemberEdit.builder()
                .name(member.getName())
                .gender(Gender.MALE)
                .password(otherPassword)
                .phone(member.getPhone())
                .email(member.getEmail())
                .birthDate(member.getBirthDate())
                .nickname(otherNickname)
                .mainAddr("부산")
                .detailAddr("전포대로")
                .zipcode("1234")
                .build();

        String json = objectMapper.writeValueAsString(memberEdit);
        //expect
        mockMvc.perform(patch("/member/{memberId}",member.getId())
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password").value(memberEdit.getPassword()))
                .andDo(print());
    }

    @Test
    @DisplayName("DELETE /member/{memberId}(삭제) 요청시 ")
    void test4() throws Exception {
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
        //expect
        mockMvc.perform(delete("/member/{memberId}",member.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(0,memberRepository.count());
    }

    @Test
    @DisplayName("POST /member 필수 내용 누락 테스트")
    void test5() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(1997,11,4);
        MemberCreate request = MemberCreate.builder()
                .name("김도도")
                .gender(Gender.MALE)
                .loginId("ehdud326")
                .loginId("dodo")
                .phone("010-3344-4954")
                .birthDate(birthDate)
                .nickname("kimdodo")
                .mainAddr("부산")
                .detailAddr("전포대로")
                .zipcode("1234")
                .build();

        String json = objectMapper.writeValueAsString(request);
        //expect
        mockMvc.perform(post("/member")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("존재 하지 않는 사용자 조회 테스트")
    void test6() throws Exception {
        //given

        //expect
        mockMvc.perform(get("/member/{memberId}",1L)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("존재 하지 않는 사용자 정보 수정 테스트")
    void test7() throws Exception {
        //given
        MemberEdit memberEdit = MemberEdit.builder()
                .name("kimdodo")
                .gender(Gender.MALE)
                .password("1234")
                .phone("010-1234-1234")
                .email("test@test.com")
                .birthDate(LocalDate.of(2024,05,23))
                .nickname("kimdodo")
                .mainAddr("부산")
                .detailAddr("전포대로")
                .zipcode("1234")
                .build();

        //expect
        mockMvc.perform(get("/member/{memberId}",1L)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberEdit)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}