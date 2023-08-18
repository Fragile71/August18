package com.poseidon.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/admin")
	public String adminIndex() {

		return "admin/index";
	}

	@PostMapping("/login")
	public String adminLogin(@RequestParam Map<String, Object> map,HttpSession session, Model model) {
		System.out.println(map);

		Map<String, Object> result = adminService.adminLogin(map);
		System.out.println(result);
		System.out.println(String.valueOf(result.get("count")).equals("1"));
		System.out.println(Integer.parseInt(String.valueOf(result.get("m_grade")))  > 5);

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
}
