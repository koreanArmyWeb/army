package com.superman.army.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.superman.army.service.SmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@RequiredArgsConstructor
public class EmailController {

    private final SmsService smsService;

    // 인증번호 전송 요청 처리
    @PostMapping("/email/send-code") 
    public String sendAuthCode(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        log.info("이메일 인증번호 전송 요청: {}", email);
        smsService.sendAuthCode(email);
        redirectAttributes.addFlashAttribute("message", "인증번호가 전송되었습니다.");
        log.info("인증번호가 전송되었습니다: {}", email);
        return "redirect:/check"; 
    }

    // 인증번호 검증 요청 처리
    @PostMapping("/email/verify-code") 
    public ResponseEntity<Map<String, String>> verifyAuthCode(@RequestParam("email") String email,
                                                               @RequestParam("authCode") String authCode) {
        log.info("인증번호 검증 요청: 이메일 - {}, 인증번호 - {}", email, authCode);
        boolean isValid = smsService.verifyAuthCode(email, authCode);
        Map<String, String> response = new HashMap<>();
        
        if (isValid) {
            log.info("인증 성공: 이메일 - {}", email);
            response.put("message", "인증이 완료되었습니다!");
            return ResponseEntity.ok(response);  // JSON 형태로 응답
        } else {
            log.warn("인증 실패: 이메일 - {}, 인증번호 - {}", email, authCode);
            response.put("message", "인증번호가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    @GetMapping("/login/signin")
    public String signinPage() {
        return "login/signin"; 
    }
    
    
    
    


    }

