package com.superman.army.repository;

import com.superman.army.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 회원 조회 (로그인용)
    Optional<Member> findByEmail(String email); 
} 
