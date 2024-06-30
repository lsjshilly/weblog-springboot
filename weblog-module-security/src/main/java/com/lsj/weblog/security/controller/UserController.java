package com.lsj.weblog.security.controller;


import com.lsj.weblog.common.aspect.ApiOperationLog;
import com.lsj.weblog.common.base.ResponseResult;
import com.lsj.weblog.common.vo.UserVo;
import com.lsj.weblog.security.domain.dto.UpdateAdminPasswordDto;
import com.lsj.weblog.security.domain.vo.UserAuth;
import com.lsj.weblog.security.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
@Api(tags = "用户管理管理接口")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/password/update")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperationLog(description = "密码修改接口")
    @ApiOperation("密码修改接口")
    public ResponseResult<Void> updatePassword(@RequestBody UpdateAdminPasswordDto updateAdminPasswordDto) {
        userService.updatePassword(updateAdminPasswordDto);
        return ResponseResult.success();
    }

    @GetMapping("/user/info")
    @ApiOperationLog(description = "获取当前用户信息")
    @ApiOperation("获取当前用户信息")
    public ResponseResult<UserVo> userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();

        UserVo userVo = new UserVo();

        BeanUtils.copyProperties(userAuth, userVo);

        return ResponseResult.success(userVo);
    }


}
