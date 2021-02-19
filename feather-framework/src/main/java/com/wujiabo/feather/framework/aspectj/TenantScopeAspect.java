package com.wujiabo.feather.framework.aspectj;

import java.lang.reflect.Method;

import com.wujiabo.feather.common.annotation.TenantScope;
import com.wujiabo.feather.framework.web.service.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.wujiabo.feather.common.core.domain.BaseEntity;
import com.wujiabo.feather.common.core.domain.entity.SysUser;
import com.wujiabo.feather.common.core.domain.model.LoginUser;
import com.wujiabo.feather.common.utils.ServletUtils;
import com.wujiabo.feather.common.utils.StringUtils;
import com.wujiabo.feather.common.utils.spring.SpringUtils;

/**
 * 数据过滤处理
 *
 * @author wujiabo
 */
@Aspect
@Component
public class TenantScopeAspect {
    /**
     * 全部数据权限
     */
    public static final String TENANT_SCOPE_ALL = "1";

    /**
     * 自定租户权限
     */
    public static final String TENANT_SCOPE_CUSTOM = "2";

    /**
     * 仅本租户数据权限
     */
    public static final String TENANT_SCOPE_SELF = "3";

    /**
     * 数据权限过滤关键字
     */
    public static final String TENANT_SCOPE = "tenantScope";

    // 配置织入点
    @Pointcut("@annotation(com.wujiabo.feather.common.annotation.TenantScope)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) throws Throwable {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint) {
        // 获得注解
        TenantScope controllerDataScope = getAnnotationLog(joinPoint);
        if (controllerDataScope == null) {
            return;
        }
        // 获取当前的用户
        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNotNull(loginUser)) {
            SysUser currentUser = loginUser.getUser();
            // 如果是超级管理员，则不过滤数据
            if (StringUtils.isNotNull(currentUser) && !currentUser.isAdmin()) {
                dataScopeFilter(joinPoint, currentUser, controllerDataScope.tenantColumn());
            }
        }
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint    切点
     * @param user         用户
     * @param tenantColumn 租户字段名
     */
    public static void dataScopeFilter(JoinPoint joinPoint, SysUser user, String tenantColumn) {
        StringBuilder sqlString = new StringBuilder();


        String tenantScope = user.getTenantScope();
        if (TENANT_SCOPE_ALL.equals(tenantScope)) {
            sqlString = new StringBuilder();
        } else if (TENANT_SCOPE_CUSTOM.equals(tenantScope)) {
            //todo
        } else if (TENANT_SCOPE_SELF.equals(tenantScope)) {
            //todo
        }

        if (StringUtils.isNotBlank(sqlString.toString())) {
            Object params = joinPoint.getArgs()[0];
            if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) params;
                baseEntity.getParams().put(TENANT_SCOPE, " AND (" + sqlString.substring(4) + ")");
            }
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private TenantScope getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(TenantScope.class);
        }
        return null;
    }
}
