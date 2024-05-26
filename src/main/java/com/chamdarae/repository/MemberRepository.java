package com.chamdarae.repository;

import com.chamdarae.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByemail(String email);
    boolean existsByloginId(String loginId);
    boolean existsBynickname(String nickname);
}
