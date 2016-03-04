package com.wujiabo.opensource.feather.service;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;

public interface UserMgmtService {

	PageBean getUsers(String userName,Integer currentPage);
}
