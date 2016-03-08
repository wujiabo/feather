package com.wujiabo.opensource.feather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.customized.sql.Sql;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private CustomizedDao customizedDao;

	@Override
	public PageBean getUsers(String userName, String screenName, Integer currentPage) {
		return customizedDao.queryForListPage(Sql.GET_USERS_BY_USERNAME,
				new Object[] { "%" + userName + "%", "%" + screenName + "%" }, currentPage);
	}
}
