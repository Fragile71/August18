package com.poseidon.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.poseidon.web.dao.LoginDAO;

@Controller
public class LoginService {
@Autowired
private LoginDAO loginDAO;

public Map<String, Object> login(Map<String, String> map) {
	
	return loginDAO.login(map);
}

public Map<String, Object> myInfo(String id) {
	
	return loginDAO.myInfo(id);
}



	
	
}
