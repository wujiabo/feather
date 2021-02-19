package com.wujiabo.feather.common.annotation;

import java.lang.annotation.*;

/**
 * 数据权限过滤注解
 *
 * @author wujiabo
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TenantScope {
    /**
     * 租户字段名
     */
    public String tenantColumn() default "TenantId";

}
