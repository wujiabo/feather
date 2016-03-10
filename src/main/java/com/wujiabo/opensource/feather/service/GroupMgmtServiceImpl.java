package com.wujiabo.opensource.feather.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.customized.sql.SqlConstants;
import com.wujiabo.opensource.feather.mybatis.dao.TGroupMapper;
import com.wujiabo.opensource.feather.mybatis.model.TGroup;
import com.wujiabo.opensource.feather.mybatis.model.TGroupExample;

@Service
public class GroupMgmtServiceImpl implements GroupMgmtService {

	@Autowired
	private CustomizedDao customizedDao;

	@Resource
	private TGroupMapper tGroupMapper;

	@Override
	public String getGroupJson() {
		TGroupExample example = new TGroupExample();
		example.setOrderByClause("GROUP_ID ASC");
		List<TGroup> groupList = tGroupMapper.selectByExample(example);

		List<Map<String, Object>> groupMapList = new ArrayList<Map<String, Object>>();
		for (TGroup group : groupList) {
			Map<String, Object> groupMap = new HashMap<String, Object>();
			groupMap.put("id", group.getGroupId());
			groupMap.put("pId", group.getGroupPid());
			groupMap.put("name", group.getGroupName());
			groupMap.put("code", group.getGroupCode());
			groupMap.put("state", group.getState());
			groupMapList.add(groupMap);
		}
		String groupJson = JSON.toJSONString(groupMapList);
		return groupJson;
	}

	@Override
	public PageBean getGroups(String groupPid, String groupCode, String groupName, Integer currentPage) {
		return customizedDao.queryForListPage(SqlConstants.GET_GROUPS_BY_LIKE_COND,
				new Object[] { groupPid, groupPid, groupPid, "%" + groupCode + "%", "%" + groupName + "%" },
				currentPage);
	}

	@Override
	public TGroup getGroupById(Integer groupId) {
		return tGroupMapper.selectByPrimaryKey(groupId);
	}

	@Override
	public void addGroup(String groupPid, String groupCode, String groupName, String state) {
		TGroup group = new TGroup();
		group.setGroupPid(StringUtils.isBlank(groupPid) ? null : Integer.valueOf(groupPid));
		group.setGroupCode(groupCode);
		group.setGroupName(groupName);
		group.setState(state);
		tGroupMapper.insertSelective(group);
	}

	@Override
	public void editGroup(String groupId, String groupCode, String groupName, String state) {
		TGroup group = tGroupMapper.selectByPrimaryKey(Integer.valueOf(groupId));
		group.setGroupCode(groupCode);
		group.setGroupName(groupName);
		group.setState(state);
		tGroupMapper.updateByPrimaryKeySelective(group);
	}
}
