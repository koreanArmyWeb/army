package com.superman.army.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.superman.army.member.Member;
import com.superman.army.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/member")
	public String getMemberInfo(@RequestParam("id") Long id ,Model model) {
		Member member = memberService.getMemberDetails(id);
		model.addAttribute("member", member);
		return "member/member";
	}
	
	
}
