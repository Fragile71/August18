package com.poseidon.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.web.dao.AdminDAO;

@Service
public class AdminService {
	@Autowired
	private AdminDAO adminDAO;

	public Map<String, Object> adminLogin(Map<String, Object> map) {

		return adminDAO.adminLogin(map);
	}

	public List<Map<String, Object>> notice() {
		
		return adminDAO.notice();
	}

	public void noticeWrite(Map<String, Object> map) {
		
		adminDAO.noticeWrite(map);
		
	}

	public Map<String, Object> noticeDetail(int nno) {
		
		return adminDAO.noticeDetail(nno);
	}

	public int noticeHide(int nno) {
		return adminDAO.noticeHide(nno);
		
	}
}