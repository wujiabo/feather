package com.wujiabo.feather.web.controller.system;

import com.wujiabo.feather.common.constant.Constants;
import com.wujiabo.feather.common.core.domain.AjaxResult;
import com.wujiabo.feather.common.core.domain.entity.SysUser;
import com.wujiabo.feather.common.core.domain.model.LoginBody;
import com.wujiabo.feather.common.core.domain.model.LoginUser;
import com.wujiabo.feather.common.utils.ServletUtils;
import com.wujiabo.feather.framework.web.service.SysLoginService;
import com.wujiabo.feather.framework.web.service.SysPermissionService;
import com.wujiabo.feather.framework.web.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 登录验证
 *
 * @author wujiabo
 */
@Api("登录验证")
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation("登录方法")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation("获取用户信息")
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRole(user);
        // 权限集合
        Set<String> permissions = permissionService.getPerms(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }
}
