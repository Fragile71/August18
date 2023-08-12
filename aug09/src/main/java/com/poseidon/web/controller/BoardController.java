package com.poseidon.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		// System.out.println(list);

		return "board";
	}

	@ResponseBody
	@PostMapping("/detail")
	public String detail(@RequestParam("bno") int bno) {
		System.out.println(bno);
		BoardDTO dto = boardService.detail(bno);
		//읽음수 +1 하기
		
		
		JSONObject json = new JSONObject();
		
		json.put("content", dto.getBcontent());
		json.put("uuid", dto.getUuid());
		
		
		//System.out.println(json.toString());
		
		return json.toString();
	}

	@GetMapping("/write")
	public String write() {
		return "write";
	}

	@PostMapping("/write")
	public String write(HttpServletRequest request) {

		//System.out.println(request.getParameter("title"));
		//System.out.println(request.getParameter("content"));
		BoardDTO dto = new BoardDTO();
        dto.setBtitle(request.getParameter("title"));
        dto.setBcontent(request.getParameter("content"));
        dto.setM_id("mingming");//임시로 member에 있는 id를 넣어주세요
        dto.setBip("0.0.0.0");
        
        int result = boardService.write(dto);
        System.out.println(result);
		
		return "redirect:/board";
	}

    @PostMapping("/delete")
    public String delete(@RequestParam Map<String,Object> map) {
    	System.out.println(map);
    
	
	

	return "redirect:/board";
}
}