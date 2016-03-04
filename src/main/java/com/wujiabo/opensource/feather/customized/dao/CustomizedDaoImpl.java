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
	public List<Map<String, Object>> queryForList(String sql,Object... args) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForList(sql, args);
	}
	

	@Override
	public Map<String, Object> queryForMap(String sql,Object... args) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForMap(sql, args);
	}
}
