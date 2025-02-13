package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // ✅ 게시글 목록 조회
    @GetMapping
    public String getAllBoards(Model model) {
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
        return "redirect:/board";
    }


    // ✅ 게시글 상세 보기
    @GetMapping("/{id}")
    public String getBoardDetail(@PathVariable Long id, Model model) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        model.addAttribute("board", board);
        return "board/detail";  // -> `src/main/resources/templates/board/detail.html`
    }

    // ✅ 게시글 삭제
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return "redirect:/board";
    }
}
