package com.superman.army.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.superman.army.member.Member;
import com.superman.army.repository.MemberRepository;
import com.superman.army.session.SessionManager;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final MemberRepository memberRepository;
	private final SessionManager sessionManager;
	

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {
    	
    	Member member = (Member)sessionManager.getSession(request);
  
    	boolean isLoggedIn = (member != null);
    	
    	// 로그인 여부를 model에 전달, member값도
        model.addAttribute("isLoggedIn", isLoggedIn);
        
        if(isLoggedIn) {
        	model.addAttribute("member", member);
        }
        
    	return "index";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
    	sessionManager.expire(request);
    	
    	return "redirect:/";
    }
    
    
    
    
	
}
