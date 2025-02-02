package com.superman.army.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.superman.army.member.Member;

@Service
public class SoldierService {
	Member member = new Member();
	
	//디데이 계산 메서드 (입대날짜를 받아서 전역날짜 계산하고 return)
	public long dDayCalculator(Date startDay) {
		LocalDate startLocalDate = startDay.toLocalDate();
		
		//전역일 계산 (18개월 후)
		LocalDate dDayLocalDate = startLocalDate.plusMonths(18);
		
		//오늘 날짜
		LocalDate today = LocalDate.now();
		
		//d-day 계산
		long dDay = ChronoUnit.DAYS.between(today, dDayLocalDate);
				
		return dDay;
	}
	
	//계급 계산 메서드 (전역일 - 입대후 지난 기간으로 계급 계산)
	public String levelCalculator(long dDay) {
		if(dDay >= 450) {
			return "이등병";
		}else if(dDay <= 449 && dDay >= 290){
			return "일병";
		}else if(dDay <= 289 && dDay <= 120){
			return "상병";
		}else {
			return "병장";
		}
	}
	
	
} 
