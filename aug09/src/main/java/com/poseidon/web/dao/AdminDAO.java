package com.poseidon.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDAO {

	Map<String, Object> adminLogin(Map<String, Object> map);

	List<Map<String, Object>> notice();

	void noticeWrite(Map<String, Object> map);

	Map<String, Object> noticeDetail(int nno);

	int noticeHide(int nno);

	List<Map<String, Object>> setupBoardList();
}
