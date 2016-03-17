package com.wujiabo.opensource.feather.service.impl;

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
import com.wujiabo.opensource.feather.enums.State;
import com.wujiabo.opensource.feather.mybatis.dao.TGroupMapper;
import com.wujiabo.opensource.feather.mybatis.dao.TGroupRoleMapper;
import com.wujiabo.opensource.feather.mybatis.model.TGroup;
import com.wujiabo.opensource.feather.mybatis.model.TGroupExample;
import com.wujiabo.opensource.feather.mybatis.model.TGroupRoleExample;
import com.wujiabo.opensource.feather.mybatis.model.TGroupRoleKey;
import com.wujiabo.opensource.feather.service.GroupMgmtService;
import com.wujiabo.opensource.feather.service.exception.ServiceException;

@Service
public class GroupMgmtServiceImpl implements GroupMgmtService {

	@Autowired
	private CustomizedDao customizedDao;

	@Resource
	private TGroupMapper tGroupMapper;

	@Resource
	private TGroupRoleMapper tGroupRoleMapper;

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
		if (StringUtils.isNotBlank(groupPid)) {
			TGroup pGroup = tGroupMapper.selectByPrimaryKey(Integer.valueOf(groupPid));
			if (State.INACTIVE.getValue().equals(pGroup.getState())) {
				throw new ServiceException("父节点处于无效状态，请先将父节点改为有效状态。");
			}
		}

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

		if (group.getGroupPid() != null) {
			TGroup pGroup = tGroupMapper.selectByPrimaryKey(Integer.valueOf(group.getGroupPid()));
			if (State.INACTIVE.getValue().equals(pGroup.getState())) {
				throw new ServiceException("父节点处于无效状态，请先将父节点改为有效状态。");
			}
		}

		group.setGroupCode(groupCode);
		group.setGroupName(groupName);
		group.setState(state);
		tGroupMapper.updateByPrimaryKeySelective(group);

		if (State.INACTIVE.getValue().equals(state)) {
			TGroupExample example = new TGroupExample();
			example.createCriteria().andGroupPidEqualTo(Integer.valueOf(groupId))
					.andStateEqualTo(State.ACTIVE.getValue());
			List<TGroup> groupList = tGroupMapper.selectByExample(example);
			for (TGroup tGroup : groupList) {
				tGroup.setState(State.INACTIVE.getValue());
				tGroupMapper.updateByPrimaryKeySelective(tGroup);
				updateChildGroup(tGroup.getGroupId());
			}
		}
	}

	private void updateChildGroup(Integer groupId) {
		TGroupExample example = new TGroupExample();
		example.createCriteria().andGroupPidEqualTo(groupId).andStateEqualTo(State.ACTIVE.getValue());
		List<TGroup> groupList = tGroupMapper.selectByExample(example);
		for (TGroup tGroup : groupList) {
			tGroup.setState(State.INACTIVE.getValue());
			tGroupMapper.updateByPrimaryKeySelective(tGroup);
			updateChildGroup(tGroup.getGroupId());
		}
	}

	@Override
	public List<Map<String, Object>> getRoleByGroupId(String groupId) {
		return customizedDao.queryForList(SqlConstants.GET_ROLES_BY_GROUPID, new Object[] { groupId });
	}

	@Override
	public void saveRoles(String groupId, String roleIds) {
		TGroupRoleExample example = new TGroupRoleExample();
		example.createCriteria().andGroupIdEqualTo(Integer.valueOf(groupId));
		tGroupRoleMapper.deleteByExample(example);
		String[] roleIds_ = roleIds.split(",");
		if (StringUtils.isNotBlank(roleIds) && roleIds_.length > 0) {
			for (String roleId : roleIds_) {
				TGroupRoleKey record = new TGroupRoleKey();
				record.setGroupId(Integer.valueOf(groupId));
				record.setRoleId(Integer.valueOf(roleId));
				tGroupRoleMapper.insertSelective(record);
			}
		}
	}
}
