package com.lsj.weblog.web.controller;


import com.lsj.weblog.common.aspect.ApiOperationLog;
import com.lsj.weblog.web.domain.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {


    @PostMapping("/user")
    @ApiOperation(value = "测试接口")
    @ApiOperationLog(description = "测试接口")
    public UserDto user(@RequestBody @Validated UserDto userDto) {


        return userDto;


    }
}
