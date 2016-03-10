package com.wujiabo.opensource.feather.service;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.model.TPermission;

public interface PermissionMgmtService {
	String getPermissionJson();

	TPermission getPermissionById(Integer permissionId);

	void addPermission(Integer permissionPid, String permissionCode, String permissionName, String state);

	void editPermission(String permissionId, String permissionCode, String permissionName, String state);

	PageBean getPermissions(String permissionPid, String permissionCode, String permissionName, Integer currentPage);
}
