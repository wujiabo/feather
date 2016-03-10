package com.wujiabo.opensource.feather.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.customized.sql.SqlConstants;
import com.wujiabo.opensource.feather.mybatis.dao.TPermissionMapper;
import com.wujiabo.opensource.feather.mybatis.model.TPermission;
import com.wujiabo.opensource.feather.mybatis.model.TPermissionExample;

@Service
public class PermissionMgmtServiceImpl implements PermissionMgmtService {

	@Autowired
	private CustomizedDao customizedDao;
	
	@Resource
	private TPermissionMapper tPermissionMapper;

	@Override
	public String getPermissionJson() {
		TPermissionExample example = new TPermissionExample();
		example.setOrderByClause("PERMISSION_ID ASC");
		List<TPermission> permissionList = tPermissionMapper.selectByExample(example);

		List<Map<String, Object>> permissionMapList = new ArrayList<Map<String, Object>>();
		for (TPermission permission : permissionList) {
			Map<String, Object> permissionMap = new HashMap<String, Object>();
			permissionMap.put("id", permission.getPermissionId());
			permissionMap.put("pId", permission.getPermissionPid());
			permissionMap.put("name", permission.getPermissionName());
			permissionMap.put("code", permission.getPermissionCode());
			permissionMap.put("state", permission.getState());
			permissionMapList.add(permissionMap);
		}
		String permissionJson = JSON.toJSONString(permissionMapList);
		return permissionJson;
	}

	@Override
	public PageBean getPermissions(String permissionPid, String permissionCode, String permissionName,
			Integer currentPage) {
		return customizedDao.queryForListPage(SqlConstants.GET_PERMISSIONS_BY_LIKE_COND,
				new Object[] { permissionPid, permissionPid, "%" + permissionCode + "%", "%" + permissionName + "%" },
				currentPage);
	}

	@Override
	public TPermission getPermissionById(Integer permissionId) {
		return tPermissionMapper.selectByPrimaryKey(permissionId);
	}

	@Override
	public void addPermission(Integer permissionPid, String permissionCode, String permissionName, String state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editPermission(String permissionId, String permissionCode, String permissionName, String state) {
		// TODO Auto-generated method stub

	}
}
