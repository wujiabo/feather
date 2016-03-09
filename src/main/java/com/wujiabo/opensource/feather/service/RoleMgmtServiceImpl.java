package com.wujiabo.opensource.feather.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.constants.SqlConstants;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.dao.TRoleMapper;
import com.wujiabo.opensource.feather.mybatis.model.TRole;

@Service
public class RoleMgmtServiceImpl implements RoleMgmtService {

	@Autowired
	private CustomizedDao customizedDao;

	@Resource
	private TRoleMapper tRoleMapper;


	@Override
	public PageBean getRoles(String roleCode, String roleName, Integer currentPage) {
		return customizedDao.queryForListPage(SqlConstants.GET_ROLES_BY_ROLENAME,
				new Object[] { "%" + roleCode + "%", "%" + roleName + "%" }, currentPage);
	}

	@Override
	public TRole getRoleById(Integer roleId) {
		return tRoleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public void addRole(String roleCode, String roleName, String state) {
		TRole role = new TRole();
		role.setRoleCode(roleCode);
		role.setRoleName(roleName);
		role.setState(state);
		tRoleMapper.insertSelective(role);
	}

	@Override
	public void editRole(String roleId, String roleName, String state) {
		TRole role = tRoleMapper.selectByPrimaryKey(Integer.valueOf(roleId));
		role.setRoleName(roleName);
		role.setState(state);
		tRoleMapper.updateByPrimaryKeySelective(role);
	}
}
