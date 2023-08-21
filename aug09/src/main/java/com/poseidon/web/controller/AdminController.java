package com.poseidon.web.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.poseidon.web.service.AdminService;
import com.poseidon.web.util.Util;

@Controller
@RequestMapping("/admin")
public class AdminController {
//AdminService /AdminDAO / adminMapper

	@Autowired
	private AdminService adminService;

	@Autowired
	private Util util;

	@GetMapping("/")
	public String adminIndex2() {

		return "forward:/admin/admin";
	}
//url 경로명을 유지하고 화면내용만 갱신합니다.

	@GetMapping("/admin")
	public String adminIndex() {

		return "admin/index";
	}

	@PostMapping("/login")
	public String adminLogin(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
		System.out.println(map);

		Map<String, Object> result = adminService.adminLogin(map);
		System.out.println(result);
		System.out.println(String.valueOf(result.get("count")).equals("1"));
		System.out.println(Integer.parseInt(String.valueOf(result.get("m_grade"))) > 5);

		if (util.str2Int(result.get("count")) == 1 && util.str2Int(result.get("m_grade")) >= 5) {
			System.out.println("좋았어! 진행시켜");
			session.setAttribute("mid", map.get("id"));
			session.setAttribute("mgrade", result.get("m_name"));
			session.setAttribute("mgade", result.get("m_grade"));
			return "redirect:/admin/main";
		} else {

			return "redirect:/admin/admin?error=login";
		}
	}

	@GetMapping("/main")
	public String main() {

		return "admin/main";// 폴더 적어줘야 admin/밑에 main.jsp를 열어줍니다..

	}

	@GetMapping("/notice")
	public String notice(Model model) {
		// 1데이터 베이스까지 연결하기
		// 2 데이터 불러오기
		// 3 데이터 jsp로 보내기

		List<Map<String, Object>> list = adminService.notice();

		model.addAttribute("list", list);

		return "admin/notice";

	}

	@PostMapping("/noticeWrite")
	public String noticeWrite(@RequestParam("upFile") MultipartFile upfile, @RequestParam Map<String, Object> map) {
		
		//upfile정보보기
	if(!upfile.isEmpty()){
	
	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
	String path = request.getServletContext().getRealPath("/upload");
	System.out.println("실제경로 :"+ path);
	
	System.out.println(upfile.getOriginalFilename());
	System.out.println(upfile.getSize());
	System.out.println(upfile.getContentType());
	
    File newFileName = new File(upfile.getOriginalFilename());
	
	}	
	
	
	map.put("mno",4);
	adminService.noticeWrite(map);
	
		return "redirect:/admin/notice";
	}
	
	
}

