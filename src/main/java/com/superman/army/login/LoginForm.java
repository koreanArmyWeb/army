package com.superman.army.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
	//로그인 한 회원들 정보
	
	@NotBlank(message = "이메일을 입력해주세요.")
	private String loginEmail;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;
	
} 
