package com.wujiabo.opensource.feather.service;

import java.util.List;
import java.util.Map;

public interface UserMgmtService {

	List<Map<String,Object>> getUsers(String userName);
}
