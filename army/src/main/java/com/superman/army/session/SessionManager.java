package com.superman.army.session;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionManager {
	public static final String SESSION_COOKIE_NAME = "mySessionId";
	private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
	
	//세션 생성 메서드
	public void createSession(Object value, HttpServletResponse response) {
		//세션 id 생성 후 값을 세션에 저장
		String sessionId = UUID.randomUUID().toString(); //UUID가 랜덤값 생성하게 해줌
		sessionStore.put(sessionId, value);
		
		//쿠키 생성
		Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		response.addCookie(mySessionCookie);  //응답값에 쿠키 넣기
	}
	
	//쿠키 조회 메서드
	private Cookie findCookie(HttpServletRequest request, String cookieName) {
		if(request.getCookies() == null) {
			return null;
		}
		
		return Arrays.stream(request.getCookies())
				.filter(cookie -> cookie.getName().equals(cookieName))
				.findAny()
				.orElse(null);
	}
	
	//세션 조회 메서드
	public Object getSession(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		
		if(sessionCookie == null) {
			return null;
		}
		
		return sessionStore.get(sessionCookie.getValue());
	}
	
	//세션 만료 메서드
	public void expire(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		
		if(sessionCookie != null) {
			sessionStore.remove(sessionCookie.getValue());
		}
	}
	
	
	
	
	
	
	
}

