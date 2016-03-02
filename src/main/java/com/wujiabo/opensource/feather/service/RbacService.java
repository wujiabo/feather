package com.wujiabo.opensource.feather.service;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

import com.wujiabo.opensource.feather.mybatis.model.TUser;

public interface RbacService {

	TUser findByUsername(String username);

	void setAuthorizationInfo(SimpleAuthorizationInfo authorizationInfo, String username);

	void createUser(TUser user);

	void changePassword(Integer userId, String newPassword);

	String getCurrentMenuJson(Integer userId);
}
