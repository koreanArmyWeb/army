package com.superman.army.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckController {
	
	@GetMapping("/check")
	public String checkFrom(Model model) {
		if (model.containsAttribute("message")) {
	        model.addAttribute("message", model.getAttribute("message"));
	    }
		
		return "check/check";
	}
	
    @PostMapping("/check")
    public String completeSignup() {
        return "login/signin"; 
    }
    
    
    
	
}
