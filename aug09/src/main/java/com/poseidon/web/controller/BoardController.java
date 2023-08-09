package com.poseidon.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.poseidon.web.dto.BoardDTO;
import com.poseidon.web.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@GetMapping("/board")
	public String board(Model model) {

		List<BoardDTO> list = boardService.boardList();
		// BoardDTO dto = new BoardDTO();
		model.addAttribute("list", list);
		System.out.println(list);

		return "board";
	}

}
