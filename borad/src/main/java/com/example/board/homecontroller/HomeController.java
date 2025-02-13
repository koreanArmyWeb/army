package com.example.board.homecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")  // ✅ 홈 페이지 요청 처리
    public String home() {
        return "index";  // ✅ `src/main/resources/templates/index.html`을 반환
    }
}
