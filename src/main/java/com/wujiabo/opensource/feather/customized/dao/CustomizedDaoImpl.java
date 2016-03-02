package com.wujiabo.opensource.feather.customized.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomizedDaoImpl implements CustomizedDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String, Object>> getUsers(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
