package com.wujiabo.feather.web.controller.tool;

import com.wujiabo.feather.common.annotation.Log;
import com.wujiabo.feather.common.config.FeatherConfig;
import com.wujiabo.feather.common.core.controller.BaseController;
import com.wujiabo.feather.common.core.domain.AjaxResult;
import com.wujiabo.feather.common.core.domain.model.LoginUser;
import com.wujiabo.feather.common.core.page.TableDataInfo;
import com.wujiabo.feather.common.enums.BusinessType;
import com.wujiabo.feather.common.utils.SecurityUtils;
import com.wujiabo.feather.common.utils.ServletUtils;
import com.wujiabo.feather.common.utils.file.FileUploadUtils;
import com.wujiabo.feather.common.utils.poi.ExcelUtil;
import com.wujiabo.feather.framework.web.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api("示例")
@RestController
@RequestMapping("/tool/demo")
public class DemoController extends BaseController {
    @Autowired
    private TokenService tokenService;

    private final static List<TestDemo> demos = new ArrayList<>();

    {
        demos.add(new TestDemo("A"));
        demos.add(new TestDemo("B"));
    }

    @ApiOperation("分页查询返回列表")
    @PreAuthorize("@ss.hasPermi('tool:demo:test01')")
    @GetMapping("/test01")
    public TableDataInfo test01() {
        startPage();
        List<TestDemo> list = demos;
        return getDataTable(list);
    }

    @ApiOperation("导出Excel")
    @Log(title = "导出Excel", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('tool:demo:test02')")
    @GetMapping("/test02")
    public AjaxResult test02() {
        ExcelUtil<TestDemo> util = new ExcelUtil<>(TestDemo.class);
        return util.exportExcel(demos, "示例数据");
    }

    @ApiOperation("导入Excel")
    @Log(title = "导入Excel", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('tool:demo:test03')")
    @PostMapping("/test03")
    public AjaxResult test03(MultipartFile file) throws Exception {
        ExcelUtil<TestDemo> util = new ExcelUtil<>(TestDemo.class);
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        logger.info("demoList:{}", util.importExcel(file.getInputStream()));
        logger.info("operName:{}", loginUser.getUsername());
        return AjaxResult.success("OK!");
    }

    @ApiOperation("导出Excel模板")
    @PreAuthorize("@ss.hasPermi('tool:demo:test04')")
    @GetMapping("/test04")
    public AjaxResult test04() {
        ExcelUtil<TestDemo> util = new ExcelUtil<>(TestDemo.class);
        return util.importTemplateExcel("示例数据");
    }

    @ApiOperation("test05")
    @PreAuthorize("@ss.hasPermi('tool:demo:test05')")
    @GetMapping(value = {"/", "/{userId}"})
    public AjaxResult test05(@PathVariable(value = "userId", required = false) String userId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put(AjaxResult.DATA_TAG, "OK" + userId);
        return ajax;
    }

    @ApiOperation("test06")
    @Log(title = "test06", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('tool:demo:test06')")
    @PostMapping("/test06")
    public AjaxResult test06(@Validated @RequestBody TestDemo testDemo) {
        if ("".equals(testDemo.getDemo())) {
            return AjaxResult.error("FAILED");
        }
        return toAjax(1);
    }

    @ApiOperation("test07")
    @Log(title = "test07", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('tool:demo:test07')")
    @PostMapping("/test07")
    public AjaxResult test07(@Validated @RequestBody TestDemo testDemo) {
        if ("".equals(testDemo.getDemo())) {
            return AjaxResult.error("FAILED");
        }
        return toAjax(1);
    }

    @ApiOperation("test08")
    @Log(title = "test08", businessType = BusinessType.DELETE)
    @PreAuthorize("@ss.hasPermi('tool:demo:test08')")
    @DeleteMapping("/{userIds}")
    public AjaxResult test08(@PathVariable String[] userIds) {
        logger.info("userIds:{}", userIds);
        return toAjax(1);
    }

    @ApiOperation("test09")
    @GetMapping("/test09")
    public AjaxResult test09() {
        logger.info("SecurityUtils.getUsername():{}", SecurityUtils.getUsername());
        logger.info("SecurityUtils.encryptPassword(AA):{}", SecurityUtils.encryptPassword("AA"));
        AjaxResult ajax = AjaxResult.success();
        ajax.put(AjaxResult.DATA_TAG, "OK" + SecurityUtils.getUsername());
        return ajax;
    }


    @ApiOperation("上传图片")
    @Log(title = "上传图片", businessType = BusinessType.UPDATE)
    @PostMapping("/test10")
    public AjaxResult test10(@RequestParam("testFile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String url = FileUploadUtils.upload(FeatherConfig.getUploadPath(), file);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", url);
            return ajax;
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }

    private class TestDemo {
        private String demo;

        public TestDemo(String demo) {
            this.demo = demo;
        }

        public String getDemo() {
            return demo;
        }

        public void setDemo(String demo) {
            this.demo = demo;
        }
    }
}
