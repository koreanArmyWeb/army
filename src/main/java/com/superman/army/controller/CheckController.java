package com.superman.army.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckController {
	
	@GetMapping("/complete-signup")
	public String checkFrom() {
		return "login/signin";
	}
	
    @PostMapping("/complete-signup")
    public String completeSignup() {
        // 여기서 필요한 인증 완료 작업을 처리
        return "redirect:/login"; 
    }
    
    
    
	
} 
