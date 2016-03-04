package com.wujiabo.opensource.feather.customized.dao;

import java.util.List;
import java.util.Map;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;


public interface CustomizedDao {

	List<Map<String, Object>> queryForList(String sql, Object[] args);

	Map<String, Object> queryForMap(String sql, Object[] args);

	PageBean queryForListPage(String sql, Object[] args, int currentPage);

}
