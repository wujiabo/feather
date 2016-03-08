package com.wujiabo.opensource.feather.service;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.model.TUser;

public interface UserMgmtService {

	PageBean getUsers(String userName, String screenName, Integer currentPage);

	TUser getUserById(Integer userId);
}
