package com.superman.army.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j 
@Service
@RequiredArgsConstructor
public class SmsService {

    private final Map<String, String> authCodeStore = new HashMap<>();

    private final JavaMailSender mailSender;

    // 인증번호 전송 로직 (이메일로 전송)
    public void sendAuthCode(String email) {
        String generatedCode = generateAuthCode();
        authCodeStore.put(email, generatedCode);

        log.info("이메일 전송 준비: {}", email);

        // 이메일 전송
        try {
            sendEmail(email, "인증번호 발송", "인증번호는 [" + generatedCode + "] 입니다.");
            log.info("인증번호 이메일 전송 완료: {}", email);
        } catch (Exception e) {
            log.error("이메일 전송 중 오류 발생: {}", e.getMessage());
        }

        log.info("전송된 인증번호 [{}]: {}", email, generatedCode);
    }

    // 인증번호 검증
    public boolean verifyAuthCode(String email, String authCode) {
        return authCodeStore.containsKey(email) && authCodeStore.get(email).equals(authCode);
    }

    // 인증번호 생성 메서드
    private String generateAuthCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000); // 6자리 랜덤 숫자
    }

    // 이메일 전송 메서드
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        //이메일 전송 시작
        log.info("이메일 발송 시작: {}", to);

        mailSender.send(message);
    }
}
