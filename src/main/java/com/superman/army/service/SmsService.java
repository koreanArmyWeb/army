package com.superman.army.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {

    private Map<String, String> authCodeStore = new HashMap<>();

    // 인증번호 전송 로직
    public void sendAuthCode(String phone) {
        String generatedCode = generateAuthCode();
        authCodeStore.put(phone, generatedCode);
        System.out.println("전송된 인증번호 [" + phone + "]: " + generatedCode);
    }

    // 인증번호 검증
    public boolean verifyAuthCode(String phone, String authCode) {
        return authCodeStore.containsKey(phone) && authCodeStore.get(phone).equals(authCode);
    }

    // 인증번호 생성 메서드
    private String generateAuthCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000); // 6자리 랜덤 숫자
    }
}
