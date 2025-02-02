package com.superman.army.service;

import org.springframework.stereotype.Service;
import com.superman.army.member.Member;
import com.superman.army.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    
    public Member login(String email, String password) {
        // 이메일로 회원 조회
        return memberRepository.findByEmail(email)  // findByEmail을 사용하여 회원을 조회
                .filter(m -> m.getPassword().equals(password)) // 비밀번호 비교
                .orElse(null);  // 비밀번호가 맞지 않거나 회원이 없으면 null 반환
    }
}  
