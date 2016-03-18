package com.wujiabo.opensource.feather.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.constants.SqlConstants;
import com.wujiabo.opensource.feather.customized.dao.Condition;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;
import com.wujiabo.opensource.feather.mybatis.dao.TGroupMapper;
import com.wujiabo.opensource.feather.mybatis.dao.TUserMapper;
import com.wujiabo.opensource.feather.mybatis.model.TGroup;
import com.wujiabo.opensource.feather.mybatis.model.TGroupExample;
import com.wujiabo.opensource.feather.mybatis.model.TUser;
import com.wujiabo.opensource.feather.mybatis.model.TUserExample;
import com.wujiabo.opensource.feather.security.PasswordHelper;
import com.wujiabo.opensource.feather.service.RbacService;

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
			tRoles.add(MapUtils.getString(role, "role_code"));
		}

		Set<String> tPermissions = new HashSet<String>();
		for (Map<String, Object> permission : permissions) {
			tPermissions.add(MapUtils.getString(permission, "permission_code"));
		}

		authorizationInfo.setRoles(tRoles);
		authorizationInfo.setStringPermissions(tPermissions);
	}

	private List<Map<String, Object>> getAllPermissionsByRoles(List<Map<String, Object>> roles) {
		List<Integer> permissionIds = new ArrayList<Integer>();
		List<Map<String, Object>> permissions = new ArrayList<Map<String, Object>>();

		roles.add(null);
		StringBuffer sb = new StringBuffer();
		if (roles != null && roles.size() > 0) {
			for (int i = 0; i < roles.size(); i++) {
				if (i == roles.size() - 1) {
					sb.append("'").append(roles.get(i) == null ? "" : roles.get(i).get("role_id")).append("'");
				} else {
					sb.append("'").append(roles.get(i) == null ? "" : roles.get(i).get("role_id")).append("'")
							.append(",");
				}
			}
		}

		List<Condition> conds = new ArrayList<Condition>();
		conds.add(new Condition("[sqlCond]", sb.toString()));

		List<Map<String, Object>> permissionList = customizedDao
				.queryForListByReplaceCond(SqlConstants.GET_ALL_PERMISSIONS_BY_ROLEID, conds, new Object[] {});
		for (Map<String, Object> permissionMap : permissionList) {
			String permissionId = MapUtils.getString(permissionMap, "permission_id");
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
			String permissionCId = MapUtils.getString(permissionMap, "permission_id");
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

		roles.add(null);
		StringBuffer sb = new StringBuffer();
		if (roles != null && roles.size() > 0) {
			for (int i = 0; i < roles.size(); i++) {
				if (i == roles.size() - 1) {
					sb.append("'").append(roles.get(i) == null ? "" : roles.get(i).get("role_id")).append("'");
				} else {
					sb.append("'").append(roles.get(i) == null ? "" : roles.get(i).get("role_id")).append("'")
							.append(",");
				}
			}
		}

		List<Condition> conds = new ArrayList<Condition>();
		conds.add(new Condition("[sqlCond]", sb.toString()));

		List<Map<String, Object>> menuList = customizedDao
				.queryForListByReplaceCond(SqlConstants.GET_ALL_MENUS_BY_ROLEID, conds, new Object[] {});

		for (Map<String, Object> menuMap : menuList) {
			String menuId = MapUtils.getString(menuMap, "menu_id");
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
			String roleId = MapUtils.getString(roleIdMap, "role_id");
			if (!roleIds.contains(Integer.valueOf(roleId))) {
				roleIds.add(Integer.valueOf(roleId));
				roles.add(roleIdMap);
			}
		}

		List<Integer> groupIds = getAllGroupIdsByUserId(userId);

		groupIds.add(null);
		StringBuffer sb = new StringBuffer();
		if (groupIds != null && groupIds.size() > 0) {
			for (int i = 0; i < groupIds.size(); i++) {
				if (i == groupIds.size() - 1) {
					sb.append("'").append(groupIds.get(i) == null ? "" : groupIds.get(i)).append("'");
				} else {
					sb.append("'").append(groupIds.get(i) == null ? "" : groupIds.get(i)).append("'").append(",");
				}
			}
		}

		List<Condition> conds = new ArrayList<Condition>();
		conds.add(new Condition("[sqlCond]", sb.toString()));

		List<Map<String, Object>> roleIdListFromGroup = customizedDao
				.queryForListByReplaceCond(SqlConstants.GET_ROLEIDS_BY_GROUPID, conds, new Object[] {});
		for (Map<String, Object> roleIdMap : roleIdListFromGroup) {
			String roleId = MapUtils.getString(roleIdMap, "role_id");
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
			String groupId = MapUtils.getString(groupIdMap, "group_id");
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
			String groupIdTemp = MapUtils.getString(groupIdMap, "group_id");
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
