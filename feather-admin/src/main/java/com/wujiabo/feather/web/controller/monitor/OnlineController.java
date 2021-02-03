package com.wujiabo.feather.web.controller.monitor;

import com.wujiabo.feather.common.annotation.Log;
import com.wujiabo.feather.common.constant.Constants;
import com.wujiabo.feather.common.core.controller.BaseController;
import com.wujiabo.feather.common.core.domain.AjaxResult;
import com.wujiabo.feather.common.core.redis.RedisCache;
import com.wujiabo.feather.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在线用户监控
 *
 * @author wujiabo
 */
@Api("在线用户监控")
@RestController
@RequestMapping("/monitor/online")
public class OnlineController extends BaseController {

    @Autowired
    private RedisCache redisCache;

    /**
     * 强退用户
     */
    @ApiOperation("强退用户")
    @PreAuthorize("@ss.hasPermi('monitor:online:forceLogout')")
    @Log(title = "在线用户", businessType = BusinessType.FORCE)
    @DeleteMapping("/{tokenId}")
    public AjaxResult forceLogout(@PathVariable String tokenId) {
        redisCache.deleteObject(Constants.LOGIN_TOKEN_KEY + tokenId);
        return AjaxResult.success();
    }
}
