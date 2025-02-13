package com.superman.army.controller;

import com.superman.army.entity.Board;
import com.superman.army.member.Member;
import com.superman.army.repository.BoardRepository;
import com.superman.army.session.SessionManager;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;
    private final SessionManager sessionManager;

    public BoardController(BoardRepository boardRepository, SessionManager sessionManager) {
        this.boardRepository = boardRepository;
		this.sessionManager = sessionManager;
    }

    // ✅ 게시글 목록 조회
    @GetMapping("/list")
    public String getAllBoards(Model model, HttpServletRequest request) {
    	Object sessionObject = sessionManager.getSession(request);
    	log.info("Session Object: {}", sessionObject);
    	
    	if (sessionObject == null) {
		    // 세션이 없다면 원래 페이지로 돌아갈 수 있도록 URL을 세션에 저장
		    request.getSession().setAttribute("redirectUrl", "/board/list");
		    return "redirect:/login/signin";  // 로그인 페이지로 리다이렉트
		}
    	
    	Member member = (Member) sessionObject;

        List<Board> boards = boardRepository.findAll();
        
        model.addAttribute("boards", boards);
        
        return "board/list";  // -> `src/main/resources/templates/board/list.html`
    }

    // ✅ 게시글 작성 폼
    @GetMapping("/new")
    public String createBoardForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/form";  // -> `src/main/resources/templates/board/form.html`
    }

    @PostMapping("/save")
    public String saveBoard(@ModelAttribute Board board) {
        if (board.getWriter() == null || board.getWriter().isEmpty()) {
            board.setWriter("익명");  // ✅ 기본값 설정
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }


    // ✅ 게시글 상세 보기
    @GetMapping("/{id}")
    public String getBoardDetail(@PathVariable("id") Long id, Model model) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        model.addAttribute("board", board);
        return "board/detail";  // -> `src/main/resources/templates/board/detail.html`
    }

    // ✅ 게시글 삭제
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardRepository.deleteById(id);
        return "redirect:/board/list";
    }
}
