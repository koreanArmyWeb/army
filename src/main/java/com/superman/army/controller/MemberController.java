package com.superman.army.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.superman.army.member.Member;
import com.superman.army.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/member")
	public String getMemberInfo(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:login/signin";
		}
		
		Member member = memberService.getMemberDetails(userId);
		
		session.setAttribute("member", member);
		model.addAttribute("member", member);
		
		return "index";
	}
	
	
	
	
}
