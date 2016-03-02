package com.wujiabo.opensource.feather.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.wujiabo.opensource.feather.constants.Constants;
import com.wujiabo.opensource.feather.mybatis.model.TUser;
import com.wujiabo.opensource.feather.service.RbacService;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
	private RbacService rbacService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        TUser user = rbacService.findByUsername(username);
        request.setAttribute(Constants.CURRENT_USER, user);
        

    	Session shiroSession = SecurityUtils.getSubject().getSession();
    	
    	if(shiroSession.getAttribute(Constants.CURRENT_MENU) == null){
    		String currentMenuJson = rbacService.getCurrentMenuJson(user.getUserId());
        	shiroSession.setAttribute(Constants.CURRENT_MENU, currentMenuJson);
    	}
        
        return true;
    }
}
