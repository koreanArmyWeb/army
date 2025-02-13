package com.superman.army.entity;
// 서치한 블로그에서는 domain 패키지 아니고 entity 패키지에.
// 원래꺼에는 @Getter@Setter@Column 없고, gettersetter수동소스코드생성하고 아래 생성자 없었음

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name="SCHEDULES")
public class Schedule {

	@JoinColumn(name="member_id")
	private Long memberId; //외래키 매핑 필요
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)// mysql의 auto_increment
   private Long scheduleId;
    
   @Column(name="schedule_date")
   private Date scheduleDate;
    
   @Column(name="schedule_title", length = 100)
   private String scheduleTitle;
   
   @Column(name="memo", length = 255)
   private String memo;

   //GetterSetter

   public Schedule(Long memberId, Long scheduleId, 
		   Date scheduleDate, String scheduleTitle, String memo) {
	   this.memberId = memberId;
	   this.scheduleId = scheduleId;
	   this.scheduleDate = scheduleDate;
	   this.scheduleTitle = scheduleTitle;
	   this.memo = memo;
   }
   
   // : 추가
   public Schedule() {
	   
   }
}
