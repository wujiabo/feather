package com.wujiabo.opensource.feather.service;

import java.util.List;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.model.TMenu;

public interface MenuMgmtService {
	String getMenuJson();

	TMenu getMenuById(Integer menuId);

	void addMenu(String menuPid, String menuUrl, String menuName, Integer seq, String state);

	void editMenu(String menuId, String menuUrl, String menuName, Integer seq, String state);

	PageBean getMenus(String menuPid, String menuUrl, String menuName, Integer currentPage);

	List<TMenu> getRootMenu();
}
