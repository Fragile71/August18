package com.poseidon.web.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.poseidon.web.dto.BoardDTO;
import com.poseidon.web.dto.MultiboardDTO;
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
		//System.out.println(Integer.parseInt(String.valueOf(result.get("m_grade"))) > 5);

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
		// 2023-08-22 요구사항확인
		if (!upfile.isEmpty()) {
			// 저장할 경로명 뽑기 request뽑기
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
			String path = request.getServletContext().getRealPath("/upload");
			System.out.println("실제 경로 : " + path);

			// upfile정보보기
			System.out.println(upfile.getOriginalFilename());
			System.out.println(upfile.getSize());
			System.out.println(upfile.getContentType());
			// 진짜로 파일 업로드 하기 경로 + 저장할 파일명
			// C:\eGovFrameDev-4.1.0-64bit\workspace\aug09\src\main\webapp\
			// upload\20171109_5a03383e9c5c4.gif
			// String타입의 경로를 file형태로 바꿔주겠습니다.
			// File filePath = new File(path);
			UUID uuid = UUID.randomUUID();
			// String realFileName = uuid.toString() + upfile.getOriginalFilename();

			// 날짜 뽑기 SimpleDataFormat
			// Date date = new Date();
			// SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
			// String dateTime =sdf.format(date);

			LocalDateTime ldt = LocalDateTime.now();
			String format = ldt.format(DateTimeFormatter.ofPattern("YYYYMMddHHmmss"));
			// 날짜 + UUID + 실제 파일명으로 사용
			String realFileName = format + uuid.toString() + upfile.getOriginalFilename();

			File newFileName = new File(path, realFileName);

			// 이제 파일 올립니다.
			try {
				// upfile.transferTo(newFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// System.out.println("저장 끝.");
			// FileCopyUtils 를 사용하기 위해서는 오리지널 파일을 byte[]로 만들어야 합니다.
			try {
				FileCopyUtils.copy(upfile.getBytes(), newFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			map.put("upFile", upfile.getOriginalFilename());
			map.put("realFile", realFileName);

		}

		map.put("mno", 4);
		System.out.println(map);
		adminService.noticeWrite(map);
		return "redirect:/admin/notice";
	}

	@GetMapping("/mail")
	public String mail() {

		return "admin/mail";
	}

	@PostMapping("/mail")
	public String mail(@RequestParam Map<String, Object> map) throws EmailException {

		util.htmlMailSender(map);

		return "admin/mail";

	}

	// noticeDetail
	@ResponseBody
	@PostMapping("/noticeDetail")
	public String noticeDetail(@RequestParam("nno") int nno) {
		System.out.println(nno);

		// jackson사용해보기
		ObjectNode json = JsonNodeFactory.instance.objectNode();

		// json.put("name", "홍길듕" );

		Map<String, Object> map = adminService.noticeDetail(nno);
		System.out.println(map);

		json.put("map", String.valueOf(map.get("ncontent")));
		// 해야할일
		// 1.데이터 베이스에 물어보기(nno로) -> 본문내용가져오기
		// 2.jackson에 담아주세요.

		return json.toString();

	}

	// noticeHide
	@ResponseBody
	@PostMapping("/noticeHide")
	public String noticeHide(@RequestParam("nno") int nno) {

		int result = adminService.noticeHide(nno);
		System.out.println(result);

		ObjectNode json = JsonNodeFactory.instance.objectNode();	
		json.put("result", result);
		
		return json.toString();
	}

	
	//2023-08-24 어플리케이션 테스트 수행
	@RequestMapping(value ="/multiBoard", method= RequestMethod.GET)
	public String multiBoard(Model model) {
		
		
		List<Map<String, Object>> list = adminService.setupBoardList(); 
		
			model.addAttribute("list", list);
		
		return "admin/multiBoard";
	}
	
	
	@PostMapping("/multiBoard")
	public String multiBoard(@RequestParam Map<String, String> map){
		System.out.println(map);
	
		int result = adminService.multiBoardInsert(map);
		
		System.out.println("result:" + result);
		return "redirect:/admin/multiBoard";
		}
	
	
	@RequestMapping(value="/member", method = RequestMethod.GET)
	public ModelAndView member(Model model){
		
		ModelAndView mv = new ModelAndView("admin/member");
		
		List<Map<String, Object>> memberList = adminService.memberList();
		
		model.addAttribute("memberList", memberList);
		
		
		return mv;
	}
	
	@GetMapping("/gradeChange")
	public String gradeChange(@RequestParam Map<String, Object> map, Model model) {
		
		
		int change = adminService.gradeChange(map);
		
		System.out.println(change);
		model.addAttribute("change",change);
		
		
		
		return"redirect:/admin/member";
	}
	
	@GetMapping("/post")
	   public String post(Model model, @RequestParam(name = "cate", required = false, defaultValue = "0") int cate,
	                  @RequestParam Map<String, Object> map) {      
	      if(!map.containsKey("cate") || map.get("cate").equals(null) || map.get("cate").equals("")) {
	         map.put("cate", 0);
	      }      
	      System.out.println(map);
	      List<Map<String, Object>> list = adminService.post(map); //게시글 전체 불러오기
	      List<Map<String, Object>> boardList = adminService.boardList(); //게시판 관리번호 불러오기
	      model.addAttribute("boardlist", boardList);
	      model.addAttribute("list", list);
	      return "/admin/post";
	   }
	
		/*
		 * @ResponseBody
		 * 
		 * @PostMapping("/detail3") public String detail(@RequestParam("mb_no") int
		 * mb_no){
		 * 
		 * Map<String, Object> map = adminService.post(mb_no); ObjectNode json =
		 * JsonNodeFactory.instance.objectNode();
		 * 
		 * 
		 * json.put("mb_no", String.valueOf(map.get("mb_no")));
		 * System.out.println(map.get("mb_no"));
		 * 
		 * 
		 * //System.out.println(json.toString());
		 * 
		 * return json.toString();
		 * 
		 * 
		 * 
		 * }
		 */
	
	
	
}
