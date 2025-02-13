package com.superman.army.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.superman.army.login.LoginForm;
import com.superman.army.member.Member;
import com.superman.army.service.LoginService;
import com.superman.army.session.SessionManager;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SigninContoller {
	
	private final LoginService loginService;
	private final SessionManager sessionManager;
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm,
							HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		log.info("Referer: {}", referer); 
		
		if (referer != null && !referer.contains("/login")) {
		    request.getSession().setAttribute("redirectUrl", referer);
		}
		
		return "login/signin";
	}
	
	@PostMapping("/login")
	public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult,
							HttpServletResponse response, Model model,
							HttpServletRequest request) {
		if (loginForm.getLoginEmail().isEmpty() || loginForm.getPassword().isEmpty()) {
			model.addAttribute("loginFail", "이메일 또는 비밀번호가 비어있습니다.");
			return "login/signin";
		}
		
		Member loginMember = loginService.login(loginForm.getLoginEmail(), loginForm.getPassword());
		log.info("loginMember={}", loginMember);
		
		if (loginMember == null) {
	        model.addAttribute("loginFail", "이메일 또는 비밀번호가 올바르지 않습니다.");
	        return "login/signin";
	    }
		
		//로그인 성공 처리
		sessionManager.createSession(loginMember, response);
		log.info("Session created for user: {}", loginMember.getId());
		log.info("sessionManager={}", sessionManager);
		
		response.addCookie(new Cookie("userId", String.valueOf(loginMember.getId())));
		
	    // 세션에서 redirectUrl을 가져와서, 해당 URL로 리다이렉트
	    String redirectUrl = (String) request.getSession().getAttribute("redirectUrl");
	    if (redirectUrl != null) {
	    	log.info("redirectUrl={}", redirectUrl);
	        return "redirect:" + redirectUrl;  // 원래 요청했던 URL로 리다이렉트
	    }

	    return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("memberId", null);
		cookie.setMaxAge(0);  //Max 를 0으로 설정해서 끝나게
		response.addCookie(cookie);
		
		return "redirect:/";
	}
	
	
	
}
