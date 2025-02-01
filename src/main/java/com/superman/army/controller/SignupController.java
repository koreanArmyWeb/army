package com.superman.army.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.superman.army.member.Member;
import com.superman.army.repository.MemberRepository;
import com.superman.army.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {
	
	private final MemberRepository memberRepository;
	
	@Autowired
    private MemberService memberService;
	
	@GetMapping("/signup")
	public String SignupPage(Model model) {
		model.addAttribute("member", new Member());
		return "signup/signup";
	}
	
	@PostMapping("/signup")
	public String save(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult,
						Model model) {
		
		if(bindingResult.hasErrors()) {
			return "signup/signup";
		}
		
		if(!member.getPassword().equals(member.getConfirmPassword())) {
			model.addAttribute("passwordError", "비밀번호가 일치하지 않습니다");
			return "signup/signup";
		}
		
		
		memberRepository.save(member);
		
		return "check/check";
	}
	
	
	
	
	
	
	
}
