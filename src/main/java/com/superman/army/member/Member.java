package com.superman.army.member;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Entity   //JPA로 테이블이랑 매핑할 거임
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotEmpty  
	private String email;
	
	@NotNull(message = "비밀번호를 입력하세요.")
	private String password;
	

	@NotNull(message = "비밀번호 확인을 입력하세요.")
	private String confirmPassword; //비밀번호 확인용
	
	@NotEmpty
	private String name;
	
	@NotNull 
	private Date startDay;
	
	private String level;
	
	private Long dDay;
	
	public boolean isValidationPassword() {
		return this.password.equals(confirmPassword);
	}
	
	// 기본 생성자 추가
	public Member() {
	}
}
