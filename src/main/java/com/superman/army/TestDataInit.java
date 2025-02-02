package com.superman.army;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.superman.army.member.Member;
import com.superman.army.repository.MemberRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataInit {
	private final MemberRepository memberRepository;
	
	@PostConstruct
	public void init() {
		Member member = new Member();
		
		member.setEmail("aloe3840@gmail.com");
		member.setPassword("qwer1234");
		member.setConfirmPassword("qwer1234");
		member.setName("홍길동");
		member.setStartDay(Date.valueOf("2025-04-30"));
		member.setLevel("상병");
		member.setDDay(240L);
		
		if(member.isValidationPassword()) {
			memberRepository.save(member);
			log.info("message={}", "회원가입 성공");
		}else {
			log.info("message={}", "비밀번호가 맞지 않습니다.");
		}
	}
	
	
} 
