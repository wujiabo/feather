package com.wujiabo.opensource.feather.shiro.util;

import com.wujiabo.opensource.feather.model.auto.TsysUser;
import com.wujiabo.opensource.feather.util.BeanUtils;
import com.wujiabo.opensource.feather.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;


/**
 * shiro 工具类
 *	
 */
public class ShiroUtils {

    private ShiroUtils(){}
    
    /**
     * 获取shiro subject
     * @return
     */
    public static Subject getSubjct()
    {
        return SecurityUtils.getSubject();
    }
    
    /**
     * 获取登录session
     * @return
     */
    public static Session getSession()
    {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 退出登录
     */
    public static void logout()
    {
        getSubjct().logout();
    }
    
    /**
     * 获取登录用户model
     * @return
     */
    public static TsysUser getUser()
    {
    	TsysUser user = null;
        Object obj = getSubjct().getPrincipal();
        if (StringUtils.isNotNull(obj))
        {
            user = new TsysUser();
            BeanUtils.copyBeanProp(user, obj);
        }
        return user;
    }
    
    /**
     * set用户
     * @param user
     */
    public static void setUser(TsysUser user)
    {
        Subject subject = getSubjct();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }
    
    /**
     * 清除授权信息
     */
    public static void clearCachedAuthorizationInfo()
    {
    }
    
    /**
     * 获取登录用户id
     * @return
     */
    public static String getUserId()
    {
        TsysUser tsysUser = getUser();
        if (tsysUser == null || tsysUser.getId() == null){
            throw new RuntimeException("用户不存在！");
        }
        return tsysUser.getId().trim();
    }

    /**
     * 获取登录用户name
     * @return
     */
    public static String getLoginName()
    {
        TsysUser tsysUser = getUser();
        if (tsysUser == null){
            throw new RuntimeException("用户不存在！");
        }
        return tsysUser.getUsername();
    }
    
    /**
     * 获取登录用户ip
     * @return
     */
    public static String getIp()
    {
        return getSubjct().getSession().getHost();
    }
    
    /**
     * 获取登录用户sessionid
     * @return
     */
    public static String getSessionId()
    {
        return String.valueOf(getSubjct().getSession().getId());
    }
}
