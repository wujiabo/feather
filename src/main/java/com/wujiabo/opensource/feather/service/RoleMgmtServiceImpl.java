package com.wujiabo.opensource.feather.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDao;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.customized.sql.SqlConstants;
import com.wujiabo.opensource.feather.mybatis.dao.TRoleMapper;
import com.wujiabo.opensource.feather.mybatis.dao.TRoleMenuMapper;
import com.wujiabo.opensource.feather.mybatis.model.TRole;
import com.wujiabo.opensource.feather.mybatis.model.TRoleMenuExample;
import com.wujiabo.opensource.feather.mybatis.model.TRoleMenuKey;

@Service
public class RoleMgmtServiceImpl implements RoleMgmtService {

	@Autowired
	private CustomizedDao customizedDao;

	@Resource
	private TRoleMapper tRoleMapper;

	@Resource
	private TRoleMenuMapper tRoleMenuMapper;

	@Override
	public PageBean getRoles(String roleCode, String roleName, Integer currentPage) {
		return customizedDao.queryForListPage(SqlConstants.GET_ROLES_BY_LIKE_COND,
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

	@Override
	public List<Map<String, Object>> getMenuByRoleId(Integer roleId) {
		return customizedDao.queryForList(SqlConstants.GET_MENUS_BY_ROLEID, new Object[] { roleId });
	}

	@Override
	public void saveMenus(String roleId, String menuIds) {
		TRoleMenuExample example = new TRoleMenuExample();
		example.createCriteria().andRoleIdEqualTo(Integer.valueOf(roleId));
		tRoleMenuMapper.deleteByExample(example);
		String[] menuIds_ = menuIds.split(",");
		if (StringUtils.isNotBlank(menuIds) && menuIds_.length > 0) {
			for (String menuId : menuIds_) {
				TRoleMenuKey record = new TRoleMenuKey();
				record.setRoleId(Integer.valueOf(roleId));
				record.setMenuId(Integer.valueOf(menuId));
				tRoleMenuMapper.insertSelective(record);
			}
		}
	}
}
