package com.wujiabo.opensource.feather.service;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.model.TGroup;

public interface GroupMgmtService {
	String getGroupJson();

	TGroup getGroupById(Integer groupId);

	void addGroup(String groupPid, String groupCode, String groupName, String state);

	void editGroup(String groupId, String groupCode, String groupName, String state);

	PageBean getGroups(String groupPid, String groupCode, String groupName, Integer currentPage);
}
