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
import com.wujiabo.opensource.feather.mybatis.dao.TMenuMapper;
import com.wujiabo.opensource.feather.mybatis.model.TMenu;
import com.wujiabo.opensource.feather.mybatis.model.TMenuExample;

@Service
public class MenuMgmtServiceImpl implements MenuMgmtService {

	@Autowired
	private CustomizedDao customizedDao;

	@Resource
	private TMenuMapper tMenuMapper;

	@Override
	public String getMenuJson() {
		TMenuExample example = new TMenuExample();
		example.setOrderByClause("MENU_ID ASC");
		List<TMenu> menuList = tMenuMapper.selectByExample(example);

		List<Map<String, Object>> menuMapList = new ArrayList<Map<String, Object>>();
		for (TMenu menu : menuList) {
			Map<String, Object> menuMap = new HashMap<String, Object>();
			menuMap.put("id", menu.getMenuId());
			menuMap.put("pId", menu.getMenuPid());
			menuMap.put("name", menu.getMenuName());
			menuMap.put("seq", menu.getSeq());
			menuMap.put("state", menu.getState());
			menuMapList.add(menuMap);
		}
		String menuJson = JSON.toJSONString(menuMapList);
		return menuJson;
	}

	@Override
	public PageBean getMenus(String menuPid, String menuUrl, String menuName, Integer currentPage) {
		return customizedDao.queryForListPage(SqlConstants.GET_MENUS_BY_LIKE_COND,
				new Object[] { menuPid, menuPid, menuPid, "%" + menuUrl + "%", "%" + menuName + "%" }, currentPage);
	}

	@Override
	public TMenu getMenuById(Integer menuId) {
		return tMenuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public void addMenu(String menuPid, String menuUrl, String menuName, Integer seq, String state) {
		TMenu menu = new TMenu();
		menu.setMenuPid(StringUtils.isBlank(menuPid) ? null : Integer.valueOf(menuPid));
		menu.setMenuUrl(menuUrl);
		menu.setMenuName(menuName);
		menu.setSeq(seq);
		menu.setState(state);
		tMenuMapper.insertSelective(menu);
	}

	@Override
	public void editMenu(String menuId, String menuUrl, String menuName, Integer seq, String state) {
		TMenu menu = tMenuMapper.selectByPrimaryKey(Integer.valueOf(menuId));
		menu.setMenuUrl(menuUrl);
		menu.setMenuName(menuName);
		menu.setSeq(seq);
		menu.setState(state);
		tMenuMapper.updateByPrimaryKeySelective(menu);
	}
}
