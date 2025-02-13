package com.superman.army.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.superman.army.entity.Schedule;
import com.superman.army.member.Member;
import com.superman.army.service.MemberService;
import com.superman.army.service.ScheduleService;
import com.superman.army.session.SessionManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RestController
@RequiredArgsConstructor // DI(의존성 주입) 중 생성자 주입: final로 선언한 필드나 @NonNull이 붙은 필드들에 대해 생성자를 생성해줌
@RequestMapping("/calendar")
public class CalendarController {
	
	private final ScheduleService scheduleService;
	private final SessionManager sessionManager;

	@GetMapping
	public String getCalendar(Model model, HttpServletRequest request) {
		 Object sessionObject = sessionManager.getSession(request);

		    if (sessionObject == null) {
		        // 세션이 없다면 원래 페이지로 돌아갈 수 있도록 URL을 세션에 저장
		        request.getSession().setAttribute("redirectUrl", "/calendar");
		        return "redirect:/login/signin";  // 로그인 페이지로 리다이렉트
		    }

		    Member member = (Member) sessionObject;

		    model.addAttribute("member", member);
		    return "calendar";  // 로그인 후 캘린더 페이지로 이동
	}	
	
	@PostMapping
	public String addCalendarForm(
			@RequestParam("scheduleDate") Date scheduleDate,
			@RequestParam("scheduleTitle") String scheduleTitle,
			@RequestParam("memo") String memo
			) {
	
		scheduleService.addSchedule(scheduleDate, scheduleTitle, memo);
		return "redirect:/calendar";
	}
	
	
	
	@ResponseBody
	@GetMapping("/api/schedules")
	public List<Object[]> getSchedules() {
		
		List<Object[]> schedules = scheduleService.getSchedulesByMemberId(1L);
		
	    return schedules;
	}
	
}
