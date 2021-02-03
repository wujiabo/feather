package com.wujiabo.feather.web.controller.monitor;

import com.wujiabo.feather.common.core.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("日志管理")
@RestController
@RequestMapping("/monitor/log")
public class LogController extends BaseController {
}
