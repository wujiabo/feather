package com.wujiabo.opensource.feather.web.shiro.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.wujiabo.opensource.feather.constants.Constants;
import com.wujiabo.opensource.feather.enums.State;
import com.wujiabo.opensource.feather.mybatis.model.TMenu;
import com.wujiabo.opensource.feather.mybatis.model.TUser;
import com.wujiabo.opensource.feather.service.RbacService;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-15
 * <p>
 * Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

	@Autowired
	private RbacService rbacService;

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {

		String username = (String) SecurityUtils.getSubject().getPrincipal();
		TUser user = rbacService.findByUsername(username);
		request.setAttribute(Constants.CURRENT_USER, user);

		String contextPath = request.getServletContext().getContextPath();
		request.setAttribute(Constants.CONTEXT_PATH, contextPath);

		Session shiroSession = SecurityUtils.getSubject().getSession();

		if (shiroSession.getAttribute(Constants.CURRENT_MENU) == null) {

			List<TMenu> menus = rbacService.getCurrentMenu(user.getUserId());

			StringBuffer menuSb = new StringBuffer();

			String pm = "<li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\""
					+ "data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\""
					+ "aria-expanded=\"false\">${pmn}$<span class=\"caret\"></span></a>"
					+ "<ul class=\"dropdown-menu\">${cm}$</ul>" + "</li>";
			String cm = "<li><a href=\"${cmu}$\">${cmn}$</a></li>";

			for (TMenu menu : menus) {
				if (State.ACTIVE.getValue().equals(menu.getState())) {
					if (StringUtils.isEmpty(menu.getMenuPid())) {
						menuSb.append(pm.replace("${pmn}$", menu.getMenuName()));
						StringBuffer cmSb = new StringBuffer();
						for (TMenu cmenu : menus) {
							if (State.ACTIVE.getValue().equals(cmenu.getState())) {
								if (!StringUtils.isEmpty(cmenu.getMenuPid())
										&& cmenu.getMenuPid() == menu.getMenuId()) {
									cmSb.append(cm.replace("${cmu}$", contextPath + cmenu.getMenuUrl())
											.replace("${cmn}$", cmenu.getMenuName()));
								}
							}
						}
						menuSb = new StringBuffer(menuSb.toString().replace("${cm}$", cmSb.toString()));
					}
				}
			}

			shiroSession.setAttribute(Constants.CURRENT_MENU, menuSb.toString());
		}

		return true;
	}
}
