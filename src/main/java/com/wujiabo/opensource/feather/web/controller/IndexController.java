package com.wujiabo.opensource.feather.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wujiabo.opensource.feather.constants.Constants;
import com.wujiabo.opensource.feather.model.TUser;
import com.wujiabo.opensource.feather.service.RbacService;
import com.wujiabo.opensource.feather.web.bind.annotation.CurrentUser;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {

	@Autowired
	private RbacService rbacService;

    @RequestMapping("/")
    public String index(@CurrentUser TUser loginUser, Model model) {
    //    model.addAttribute("menus", menus);
    	Session shiroSession = SecurityUtils.getSubject().getSession();
    	
    	if(shiroSession.getAttribute(Constants.CURRENT_MENU) == null){
    		String currentMenuJson = rbacService.getCurrentMenuJson(loginUser.getUserId());
        	shiroSession.setAttribute(Constants.CURRENT_MENU, currentMenuJson);
    	}
        return "index";
    }

    /**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/testr")
    @ResponseBody
    @RequiresRoles(value = "ADMIN")
    public String admin() {
        return "拥有角色,能访问";
    }

    /**
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/testp")
    @ResponseBody
    @RequiresPermissions(value = "USER_CREATE")
    public String create() {
        return "拥有权限,能访问";
    }


}
