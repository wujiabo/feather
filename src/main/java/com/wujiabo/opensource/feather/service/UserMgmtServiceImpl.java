package com.wujiabo.opensource.feather.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;
import com.wujiabo.opensource.feather.customized.sql.Sql;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private CustomizedDao customizedDao;

	@Override
	public List<Map<String, Object>> getUsers(String userName) {
		return customizedDao.queryForList(Sql.GET_USERS_BY_USERNAME, new Object[] { "%" + userName + "%" });
	}
}
