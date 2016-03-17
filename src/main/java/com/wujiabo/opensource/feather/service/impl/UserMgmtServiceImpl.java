package com.wujiabo.opensource.feather.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.constants.SqlConstants;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.dao.TUserGroupMapper;
import com.wujiabo.opensource.feather.mybatis.dao.TUserMapper;
import com.wujiabo.opensource.feather.mybatis.dao.TUserRoleMapper;
import com.wujiabo.opensource.feather.mybatis.model.TUser;
import com.wujiabo.opensource.feather.mybatis.model.TUserGroupExample;
import com.wujiabo.opensource.feather.mybatis.model.TUserGroupKey;
import com.wujiabo.opensource.feather.mybatis.model.TUserRoleExample;
import com.wujiabo.opensource.feather.mybatis.model.TUserRoleKey;
import com.wujiabo.opensource.feather.service.RbacService;
import com.wujiabo.opensource.feather.service.UserMgmtService;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private CustomizedDao customizedDao;

	@Resource
	private TUserMapper tUserMapper;

	@Resource
	private TUserRoleMapper tUserRoleMapper;

	@Resource
	private TUserGroupMapper tUserGroupMapper;

	@Autowired
	private RbacService rbacService;

	@Override
	public PageBean getUsers(String userName, String screenName, Integer currentPage) {
		return customizedDao.queryForListPage(SqlConstants.GET_USERS_BY_LIKE_COND,
				new Object[] { "%" + userName + "%", "%" + screenName + "%" }, currentPage);
	}

	@Override
	public TUser getUserById(Integer userId) {
		return tUserMapper.selectByPrimaryKey(userId);
	}

	@Override
	public void addUser(String userName, String screenName, String state) {
		TUser user = new TUser();
		user.setUserName(userName);
		user.setScreenName(screenName);
		user.setState(state);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setPassword("123456");
		rbacService.createUser(user);
	}

	@Override
	public void editUser(String userId, String screenName, String state) {
		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
		user.setScreenName(screenName);
		user.setState(state);
		user.setUpdateTime(new Date());
		tUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<Map<String, Object>> getGroupByUserId(Integer userId) {
		return customizedDao.queryForList(SqlConstants.GET_GROUPS_BY_USERID, new Object[] { userId });
	}

	@Override
	public List<Map<String, Object>> getRoleByUserId(String userId) {
		return customizedDao.queryForList(SqlConstants.GET_ROLES_BY_USERID, new Object[] { userId });
	}

	@Override
	public void saveGroups(String userId, String groupIds) {
		TUserGroupExample example = new TUserGroupExample();
		example.createCriteria().andUserIdEqualTo(Integer.valueOf(userId));
		tUserGroupMapper.deleteByExample(example);
		String[] groupIds_ = groupIds.split(",");
		if (StringUtils.isNotBlank(groupIds) && groupIds_.length > 0) {
			for (String groupId : groupIds_) {
				TUserGroupKey record = new TUserGroupKey();
				record.setUserId(Integer.valueOf(userId));
				record.setGroupId(Integer.valueOf(groupId));
				tUserGroupMapper.insertSelective(record);
			}
		}
	}

	@Override
	public void saveRoles(String userId, String roleIds) {
		TUserRoleExample example = new TUserRoleExample();
		example.createCriteria().andUserIdEqualTo(Integer.valueOf(userId));
		tUserRoleMapper.deleteByExample(example);
		String[] roleIds_ = roleIds.split(",");
		if (StringUtils.isNotBlank(roleIds) && roleIds_.length > 0) {
			for (String roleId : roleIds_) {
				TUserRoleKey record = new TUserRoleKey();
				record.setUserId(Integer.valueOf(userId));
				record.setRoleId(Integer.valueOf(roleId));
				tUserRoleMapper.insertSelective(record);
			}
		}
	}
}
