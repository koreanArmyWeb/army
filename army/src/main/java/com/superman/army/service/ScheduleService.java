package com.superman.army.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.superman.army.entity.Schedule;
import com.superman.army.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

   private final ScheduleRepository scheduleRepository;
   
   @Transactional
   public void addSchedule(Date scheduleDate, String scheduleTitle, String memo) {

	  Long memberId = 1L; // 외래키땜에 임의로 1로 설정 (member 테이블 member_id에 1값 넣어놓음.)
	  Schedule schedule = new Schedule(memberId, null, scheduleDate, scheduleTitle, memo);
	  // 위 null 맞나? 걍 빼야되나?
	  scheduleRepository.save(schedule);

	  	  
//	  Schedule schedule = new Schedule();
//      schedule.setMember_id(member_id);
//      schedule.setSchedule_id(schedule_id);
//      schedule.setSchedule_date(schedule_date);
//      schedule.setSchedule_title(schedule_title);
//      schedule.setMemo(memo);
	  
   }
   
   // 특정 회원(memberId)의 모든 스케줄 조회
   public List<Object[]> getSchedulesByMemberId(Long memberId) {
	   return scheduleRepository.findByMemberId(memberId);
   }
   
   // 특정 기간(startDate ~ endDate) 동안의 스케줄 조회
//   public List<Schedule> getSchedulesBetweenDates(Date startDate, Date endDate) {
//	   return scheduleRepository.findByScheduleDateBetween(startDate, endDate);
//   }
   
//   public List<Schedule> getAllSchedules() {
//	   return scheduleRepository.findByMemberId(1L);
//   }
   

   // 문자열 값 받을 때 인코딩 변환하는 메서드
//   private String convertToUTF8(String value) {
//	   try {
//		   byte[] utf8Bytes = value.getBytes("UTF-8");
//		   return new String(utf8Bytes, "UTF-8");
//	   }catch (UnsupportedEncodingException e) {
//		   return value;
//	   }
//   }
}
