package com.wujiabo.opensource.feather.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.customized.sql.SqlConstants;
import com.wujiabo.opensource.feather.mybatis.dao.TUserMapper;
import com.wujiabo.opensource.feather.mybatis.model.TUser;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private CustomizedDao customizedDao;

	@Resource
	private TUserMapper tUserMapper;

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
		return customizedDao.queryForList(SqlConstants.GET_GROUPS_BY_USERID, new Object[]{userId});
	}
}
