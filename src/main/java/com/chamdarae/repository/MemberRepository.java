package com.chamdarae.repository;

import com.chamdarae.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByMemberEmail(String memberEmail);
    boolean existsByMemberLoginId(String memberLoginId);
    boolean existsByMemberNickname(String memberNickname);
}
