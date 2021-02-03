package com.wujiabo.feather.web.controller.monitor;

import com.wujiabo.feather.framework.web.domain.Server;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wujiabo.feather.common.core.domain.AjaxResult;

/**
 * 服务器监控
 *
 * @author wujiabo
 */
@Api("服务器监控")
@RestController
@RequestMapping("/monitor/server")
public class ServerController {

    @ApiOperation("获取服务器信息")
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
