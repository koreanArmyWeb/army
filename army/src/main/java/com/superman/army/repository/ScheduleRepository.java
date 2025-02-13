package com.superman.army.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.superman.army.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
   
//   Schedule save(Schedule schedule); // 저장 이런것들이 안써도 다 들어있어서 자유롭게 쓸 수 있음
	
	// 여기서 사용한 Schedule은 테이블 이름이 아니라 클래스 이름이므로 앞에 대문자 사용해야 함
//	@Query("select s from Schedule s where s.schedule_date between :startDate and :endDate")
//	List<Schedule> findScheduleBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	// 추가 :
	// Spring Data JPA는 메서드이름에서 findByMemberId를 보면,
	// member_id 컬럼을 조건으로 검색하는 쿼리를 자동으로 만들어줌
	// 자동생성 예상 : select * from schedules where member_id = ?;
	// 난 여기다가 1L을 넣으면 될 것임.
	@Query("SELECT s.scheduleDate, s.scheduleTitle, s.memo FROM Schedule s WHERE s.memberId = :memberId")
	List<Object[]> findByMemberId(@Param("memberId") Long memberId);

	// 추가 :
	// 자동생성 예상 : select * from schedules where schedule_date between ? and ?;
	// 저 물음표 ? 안에 매개변수가 들어가는 거.
	//List<Schedule> findByScheduleDateBetween(Date startDate, Date endDate);
	// 저거 데이터 타입 String으로 하는 거 맞는지 한 번 더 검토
}
