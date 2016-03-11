package com.wujiabo.opensource.feather.service;

import java.util.List;
import java.util.Map;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.model.TUser;

public interface UserMgmtService {

	PageBean getUsers(String userName, String screenName, Integer currentPage);

	TUser getUserById(Integer userId);

	void addUser(String userName, String screenName, String state);

	void editUser(String userId, String screenName, String state);

	List<Map<String, Object>> getGroupByUserId(Integer userId);
}
