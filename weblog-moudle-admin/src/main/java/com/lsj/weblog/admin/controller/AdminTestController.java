package com.lsj.weblog.admin.controller;


import com.lsj.weblog.common.aspect.ApiOperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Api(tags = "管理后台接口")
public class AdminTestController {

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    @ApiOperationLog(description = "测试更新接口")
    @ApiOperation("测试更新接口")
    public String update() {
        return "ok";
    }

    @ApiOperationLog(description = "测试查询接口")
    @ApiOperation("测试查询接口")
    @GetMapping("/test")
    public String test2() {
        return "hello";
    }

}
