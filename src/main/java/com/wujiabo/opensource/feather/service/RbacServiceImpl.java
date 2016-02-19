package com.wujiabo.opensource.feather.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.dao.TGroupMapper;
import com.wujiabo.opensource.feather.dao.TGroupRoleMapper;
import com.wujiabo.opensource.feather.dao.TMenuMapper;
import com.wujiabo.opensource.feather.dao.TPermissionMapper;
import com.wujiabo.opensource.feather.dao.TRoleMapper;
import com.wujiabo.opensource.feather.dao.TRoleMenuMapper;
import com.wujiabo.opensource.feather.dao.TRolePermissionMapper;
import com.wujiabo.opensource.feather.dao.TUserGroupMapper;
import com.wujiabo.opensource.feather.dao.TUserMapper;
import com.wujiabo.opensource.feather.dao.TUserRoleMapper;
import com.wujiabo.opensource.feather.model.TGroup;
import com.wujiabo.opensource.feather.model.TGroupExample;
import com.wujiabo.opensource.feather.model.TGroupRoleExample;
import com.wujiabo.opensource.feather.model.TGroupRoleKey;
import com.wujiabo.opensource.feather.model.TMenu;
import com.wujiabo.opensource.feather.model.TPermission;
import com.wujiabo.opensource.feather.model.TPermissionExample;
import com.wujiabo.opensource.feather.model.TRole;
import com.wujiabo.opensource.feather.model.TRoleMenuExample;
import com.wujiabo.opensource.feather.model.TRoleMenuKey;
import com.wujiabo.opensource.feather.model.TRolePermissionExample;
import com.wujiabo.opensource.feather.model.TRolePermissionKey;
import com.wujiabo.opensource.feather.model.TUser;
import com.wujiabo.opensource.feather.model.TUserExample;
import com.wujiabo.opensource.feather.model.TUserGroupExample;
import com.wujiabo.opensource.feather.model.TUserGroupKey;
import com.wujiabo.opensource.feather.model.TUserRoleExample;
import com.wujiabo.opensource.feather.model.TUserRoleKey;

@Service
public class RbacServiceImpl implements RbacService {

	@Resource
	private TUserMapper tUserMapper;

	@Resource
	private TGroupMapper tGroupMapper;

	@Resource
	private TRoleMapper tRoleMapper;

	@Resource
	private TPermissionMapper tPermissionMapper;

	@Resource
	private TUserGroupMapper tUserGroupMapper;

	@Resource
	private TUserRoleMapper tUserRoleMapper;

	@Resource
	private TGroupRoleMapper tGroupRoleMapper;

	@Resource
	private TRolePermissionMapper tRolePermissionMapper;

	@Resource
	private TMenuMapper tMenuMapper;

	@Resource
	private TRoleMenuMapper tRoleMenuMapper;

	@Autowired
	private PasswordHelper passwordHelper;

	@Override
	public void setAuthorizationInfo(SimpleAuthorizationInfo authorizationInfo, String username) {

		TUser user = findByUsername(username);

		List<Integer> roleIds = getAllRoleIdsByUserId(user.getUserId());

		List<Integer> permissionIds = new ArrayList<Integer>();

		Set<String> tRoles = new HashSet<String>();
		for (Integer roleId : roleIds) {
			TRole tRole = tRoleMapper.selectByPrimaryKey(roleId);
			tRoles.add(tRole.getRoleCode());

			TRolePermissionExample tRolePermissionExample = new TRolePermissionExample();
			tRolePermissionExample.createCriteria().andRoleIdEqualTo(tRole.getRoleId());
			List<TRolePermissionKey> tRolePermissions = tRolePermissionMapper.selectByExample(tRolePermissionExample);
			for (TRolePermissionKey tRolePermission : tRolePermissions) {
				Integer permissionId = tRolePermission.getPermissionId();
				if (!permissionIds.contains(permissionId)) {
					permissionIds.add(permissionId);

					List<Integer> permissionCids = new ArrayList<Integer>();
					getPermissionCidsByPermissionPid(permissionCids, permissionId);

					for (Integer permissionCid : permissionCids) {
						if (!permissionIds.contains(permissionCid)) {
							permissionIds.add(permissionCid);
						}
					}
				}
			}

		}

		Set<String> tPermissions = new HashSet<String>();
		for (Integer permissionId : permissionIds) {
			TPermission tPermission = tPermissionMapper.selectByPrimaryKey(permissionId);
			tPermissions.add(tPermission.getPermissionCode());
		}

		authorizationInfo.setRoles(tRoles);
		authorizationInfo.setStringPermissions(tPermissions);
	}

	private List<Integer> getAllRoleIdsByUserId(Integer userId) {

		List<Integer> roleIds = new ArrayList<Integer>();

		TUserRoleExample tUserRoleExample = new TUserRoleExample();
		tUserRoleExample.createCriteria().andUserIdEqualTo(userId);
		List<TUserRoleKey> tUserRoles = tUserRoleMapper.selectByExample(tUserRoleExample);
		for (TUserRoleKey tUserRole : tUserRoles) {
			if (!roleIds.contains(tUserRole.getRoleId())) {
				roleIds.add(tUserRole.getRoleId());
			}
		}

		TUserGroupExample tUserGroupExample = new TUserGroupExample();
		tUserGroupExample.createCriteria().andUserIdEqualTo(userId);
		List<TUserGroupKey> tUserGroups = tUserGroupMapper.selectByExample(tUserGroupExample);
		for (TUserGroupKey tUserGroup : tUserGroups) {
			Integer groupId = tUserGroup.getGroupId();

			TGroupRoleExample tGroupRoleExample = new TGroupRoleExample();
			tGroupRoleExample.createCriteria().andGroupIdEqualTo(groupId);
			List<TGroupRoleKey> tGroupRoles = tGroupRoleMapper.selectByExample(tGroupRoleExample);
			for (TGroupRoleKey tGroupRole : tGroupRoles) {
				if (!roleIds.contains(tGroupRole.getRoleId())) {
					roleIds.add(tGroupRole.getRoleId());
				}
			}
			List<Integer> groupCids = new ArrayList<Integer>();
			getGroupCidsByGroupPid(groupCids, groupId);
			for (Integer groupCid : groupCids) {
				TGroupRoleExample tGroupRoleExample_ = new TGroupRoleExample();
				tGroupRoleExample_.createCriteria().andGroupIdEqualTo(groupCid);
				List<TGroupRoleKey> tGroupRoles_ = tGroupRoleMapper.selectByExample(tGroupRoleExample_);
				for (TGroupRoleKey tGroupRole : tGroupRoles_) {
					if (!roleIds.contains(tGroupRole.getRoleId())) {
						roleIds.add(tGroupRole.getRoleId());
					}
				}
			}
		}
		return roleIds;
	}

	private void getGroupCidsByGroupPid(List<Integer> groupCids, Integer groupId) {
		TGroupExample tGroupExample = new TGroupExample();
		tGroupExample.createCriteria().andGroupPidEqualTo(groupId);
		List<TGroup> tGroups = tGroupMapper.selectByExample(tGroupExample);
		for (TGroup tGroup : tGroups) {
			if (!groupCids.contains(tGroup.getGroupId())) {
				groupCids.add(tGroup.getGroupId());
				getGroupCidsByGroupPid(groupCids, tGroup.getGroupId());
			}
		}
	}

	private void getPermissionCidsByPermissionPid(List<Integer> permissionCids, Integer permissionId) {

		TPermissionExample tPermissionExample = new TPermissionExample();
		tPermissionExample.createCriteria().andPermissionPidEqualTo(permissionId);
		List<TPermission> tPermissions = tPermissionMapper.selectByExample(tPermissionExample);
		for (TPermission tPermission : tPermissions) {
			if (!permissionCids.contains(tPermission.getPermissionId())) {
				permissionCids.add(tPermission.getPermissionId());
				getGroupCidsByGroupPid(permissionCids, tPermission.getPermissionId());
			}
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
	public String getCurrentMenuJson(Integer userId) {
		
		List<Integer> menuIds = new ArrayList<Integer>();

		List<Integer> roleIds = getAllRoleIdsByUserId(userId);
		for (Integer roleId : roleIds) {
			TRoleMenuExample example = new TRoleMenuExample();
			example.createCriteria().andRoleIdEqualTo(roleId);
			List<TRoleMenuKey> tRoleMenuList = tRoleMenuMapper.selectByExample(example);
			for(TRoleMenuKey tRoleMenu:tRoleMenuList){
				if(!menuIds.contains(tRoleMenu.getMenuId())){
					menuIds.add(tRoleMenu.getMenuId());
				}
			}
		}

		List<TMenu> menus = new ArrayList<TMenu>();
		for(Integer menuId:menuIds){
			TMenu tMenu = tMenuMapper.selectByPrimaryKey(menuId);
			menus.add(tMenu);
		}
		
		return null;
	}
}
