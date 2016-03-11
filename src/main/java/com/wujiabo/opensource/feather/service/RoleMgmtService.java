package com.wujiabo.opensource.feather.service;

import java.util.List;
import java.util.Map;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.model.TRole;

public interface RoleMgmtService {

	PageBean getRoles(String roleCode, String roleName, Integer currentPage);

	TRole getRoleById(Integer roleId);

	void addRole(String roleCode, String roleName, String state);

	void editRole(String roleId, String roleName, String state);

	List<Map<String, Object>> getMenuByRoleId(Integer roleId);

	void saveMenus(String roleId, String menuIds);
}
