package com.wujiabo.opensource.feather.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;
import com.wujiabo.opensource.feather.customized.sql.Sql;
import com.wujiabo.opensource.feather.customized.sql.SqlConstants;
import com.wujiabo.opensource.feather.mybatis.dao.TGroupMapper;
import com.wujiabo.opensource.feather.mybatis.dao.TUserMapper;
import com.wujiabo.opensource.feather.mybatis.model.TGroup;
import com.wujiabo.opensource.feather.mybatis.model.TGroupExample;
import com.wujiabo.opensource.feather.mybatis.model.TUser;
import com.wujiabo.opensource.feather.mybatis.model.TUserExample;

@Service
public class RbacServiceImpl implements RbacService {

	@Resource
	private TUserMapper tUserMapper;

	@Resource
	private TGroupMapper tGroupMapper;

	@Autowired
	private PasswordHelper passwordHelper;

	@Autowired
	private CustomizedDao customizedDao;

	@Override
	public void setAuthorizationInfo(SimpleAuthorizationInfo authorizationInfo, String username) {

		TUser user = findByUsername(username);

		List<Map<String, Object>> roles = getAllRolesByUserId(user.getUserId());

		List<Map<String, Object>> permissions = getAllPermissionsByRoles(roles);

		Set<String> tRoles = new HashSet<String>();
		for (Map<String, Object> role : roles) {
			tRoles.add(role.get("role_code") == null ? null : role.get("role_code").toString());
		}

		Set<String> tPermissions = new HashSet<String>();
		for (Map<String, Object> permission : permissions) {
			tPermissions.add(
					permission.get("permission_code") == null ? null : permission.get("permission_code").toString());
		}

		authorizationInfo.setRoles(tRoles);
		authorizationInfo.setStringPermissions(tPermissions);
	}

	private List<Map<String, Object>> getAllPermissionsByRoles(List<Map<String, Object>> roles) {
		List<Integer> permissionIds = new ArrayList<Integer>();
		List<Map<String, Object>> permissions = new ArrayList<Map<String, Object>>();

		String sqlCond = "";
		for (Map<String, Object> role : roles) {
			sqlCond = sqlCond + role.get("role_id") + ",";
		}
		if (sqlCond.length() > 0) {
			sqlCond = sqlCond.substring(0, sqlCond.length() - 1);
		}

		List<Map<String, Object>> permissionList = customizedDao.queryForListBySql(Sql.getInstance()
				.getSqlConfig(SqlConstants.GET_ALL_PERMISSIONS_BY_ROLEID).replace("[sqlCond]", sqlCond),
				new Object[] {});
		for (Map<String, Object> permissionMap : permissionList) {
			String permissionId = permissionMap.get("permission_id") == null ? null
					: permissionMap.get("permission_id").toString();
			if (!permissionIds.contains(Integer.valueOf(permissionId))) {
				permissionIds.add(Integer.valueOf(permissionId));
				permissions.add(permissionMap);
				setPermissions(permissionIds, permissions, permissionId);
			}
		}

		return permissions;
	}

	private void setPermissions(List<Integer> permissionIds, List<Map<String, Object>> permissions,
			String permissionId) {
		List<Map<String, Object>> permissionList = customizedDao.queryForList(SqlConstants.GET_PERMISSIONS_BY_PID,
				new Object[] { permissionId });
		for (Map<String, Object> permissionMap : permissionList) {
			String permissionCId = permissionMap.get("permission_id") == null ? null
					: permissionMap.get("permission_id").toString();
			if (!permissionIds.contains(Integer.valueOf(permissionCId))) {
				permissionIds.add(Integer.valueOf(permissionCId));
				permissions.add(permissionMap);
				setPermissions(permissionIds, permissions, permissionCId);
			}
		}
	}

	private List<Map<String, Object>> getAllMenusByRoles(List<Map<String, Object>> roles) {

		List<Integer> menuIds = new ArrayList<Integer>();
		List<Map<String, Object>> menus = new ArrayList<Map<String, Object>>();

		String sqlCond = "";
		for (Map<String, Object> role : roles) {
			sqlCond = sqlCond + role.get("role_id") + ",";
		}
		if (sqlCond.length() > 0) {
			sqlCond = sqlCond.substring(0, sqlCond.length() - 1);
		}

		List<Map<String, Object>> menuList = customizedDao.queryForListBySql(
				Sql.getInstance().getSqlConfig(SqlConstants.GET_ALL_MENUS_BY_ROLEID).replace("[sqlCond]", sqlCond),
				new Object[] {});

		for (Map<String, Object> menuMap : menuList) {
			String menuId = menuMap.get("menu_id") == null ? null : menuMap.get("menu_id").toString();
			if (!menuIds.contains(Integer.valueOf(menuId))) {
				menuIds.add(Integer.valueOf(menuId));
				menus.add(menuMap);

			}
		}
		return menus;

	}

	private List<Map<String, Object>> getAllRolesByUserId(Integer userId) {
		List<Integer> roleIds = new ArrayList<Integer>();
		List<Map<String, Object>> roles = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> roleIdList = customizedDao.queryForList(SqlConstants.GET_ROLEIDS_BY_USERID,
				new Object[] { userId });
		for (Map<String, Object> roleIdMap : roleIdList) {
			String roleId = roleIdMap.get("role_id") == null ? null : roleIdMap.get("role_id").toString();
			if (!roleIds.contains(Integer.valueOf(roleId))) {
				roleIds.add(Integer.valueOf(roleId));
				roles.add(roleIdMap);
			}
		}

		String sqlCond = "";
		List<Integer> groupIds = getAllGroupIdsByUserId(userId);
		for (Integer groupId : groupIds) {
			sqlCond = sqlCond + groupId + ",";
		}
		if (sqlCond.length() > 0) {
			sqlCond = sqlCond.substring(0, sqlCond.length() - 1);
		}

		List<Map<String, Object>> roleIdListFromGroup = customizedDao.queryForListBySql(
				Sql.getInstance().getSqlConfig(SqlConstants.GET_ROLEIDS_BY_GROUPID).replace("[sqlCond]", sqlCond),
				null);
		for (Map<String, Object> roleIdMap : roleIdListFromGroup) {
			String roleId = roleIdMap.get("role_id") == null ? null : roleIdMap.get("role_id").toString();
			if (!roleIds.contains(Integer.valueOf(roleId))) {
				roleIds.add(Integer.valueOf(roleId));
				roles.add(roleIdMap);
			}
		}

		return roles;
	}

	private List<Integer> getAllGroupIdsByUserId(Integer userId) {

		List<Integer> groupIds = new ArrayList<Integer>();

		List<Map<String, Object>> groupIdList = customizedDao.queryForList(SqlConstants.GET_GROUPIDS_BY_USERID,
				new Object[] { userId });
		for (Map<String, Object> groupIdMap : groupIdList) {
			String groupId = groupIdMap.get("group_id") == null ? null : groupIdMap.get("group_id").toString();
			setGroupIds(groupIds, groupId);
		}
		return groupIds;
	}

	private void setGroupIds(List<Integer> groupIds, String groupId) {
		if (!groupIds.contains(Integer.valueOf(groupId))) {
			groupIds.add(Integer.valueOf(groupId));
		}
		List<Map<String, Object>> groupIdList = customizedDao.queryForList(SqlConstants.GET_GROUPIDS_BY_PID,
				new Object[] { groupId });
		for (Map<String, Object> groupIdMap : groupIdList) {
			String groupIdTemp = groupIdMap.get("group_id") == null ? null : groupIdMap.get("group_id").toString();
			setGroupIds(groupIds, groupIdTemp);
		}
	}

	@Override
	public TUser findByUsername(String username) {
		TUserExample example = new TUserExample();
		example.createCriteria().andUserNameEqualTo(username);
		final List<TUser> list = tUserMapper.selectByExample(example);
		return list.size() == 0 ? null : list.get(0);
	}

	@Override
	public void createUser(TUser user) {
		// 加密密码
		passwordHelper.encryptPassword(user);
		tUserMapper.insertSelective(user);
	}

	@Override
	public void changePassword(Integer userId, String newPassword) {
		TUser user = tUserMapper.selectByPrimaryKey(userId);
		user.setPassword(newPassword);
		passwordHelper.encryptPassword(user);
		tUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<Map<String, Object>> getCurrentMenu(Integer userId) {
		List<Map<String, Object>> roles = getAllRolesByUserId(userId);
		List<Map<String, Object>> menus = getAllMenusByRoles(roles);

		Collections.sort(menus, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> arg0, Map<String, Object> arg1) {
				return Integer.valueOf(arg0.get("seq").toString())
						.compareTo(Integer.valueOf(arg1.get("seq").toString()));
			}
		});

		return menus;
	}

	@Override
	public List<TGroup> getAllGroup() {
		TGroupExample example = new TGroupExample();
		example.setOrderByClause("GROUP_ID ASC");
		return tGroupMapper.selectByExample(example);
	}
}
