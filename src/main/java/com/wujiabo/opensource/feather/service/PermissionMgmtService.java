package com.wujiabo.opensource.feather.service;

import com.wujiabo.opensource.feather.mybatis.model.TPermission;

public interface PermissionMgmtService {
	String getPermissionJson();

	TPermission getPermissionById(Integer permissionId);

	void addPermission(Integer permissionPid, String permissionCode, String permissionName, String state);

	void editPermission(String permissionId, String permissionCode, String permissionName, String state);
}
