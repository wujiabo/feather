package com.wujiabo.opensource.feather.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;


@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private CustomizedDao customizedDao;
	
	@Override
	public List<Map<String, Object>> getUsers(String userName) {
		// TODO Auto-generated method stub
		return customizedDao.getUsers(userName);
	}
}
