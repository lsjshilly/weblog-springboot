package com.lsj.weblog.admin.controller;


import com.lsj.weblog.admin.model.dto.UpdateBlogSettingReqDto;
import com.lsj.weblog.admin.model.vo.BlogSettingVo;
import com.lsj.weblog.admin.service.BlogSettingService;
import com.lsj.weblog.common.aspect.ApiOperationLog;
import com.lsj.weblog.common.base.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Api(tags = "博客设置管理接口")
@RequiredArgsConstructor
public class BlogSettingController {

    private final BlogSettingService blogSettingService;


    @PutMapping("/blog/setting/update")
    @ApiOperationLog(description = "更新博客设置接口")
    @ApiOperation("更新博客设置接口")
    public ResponseResult<Void> updateBlogSetting(@RequestBody @Validated UpdateBlogSettingReqDto updateBlogSettingReqDto) {
        blogSettingService.updateBlogSetting(updateBlogSettingReqDto);
        return ResponseResult.success();
    }


    @GetMapping("/blog/setting")
    @ApiOperationLog(description = "查询博客设置详情接口")
    @ApiOperation("查询博客设置详情接口")
    public ResponseResult<BlogSettingVo> getBlogSettingDetail() {
        BlogSettingVo detail = blogSettingService.findDetail();
        return ResponseResult.success(detail);
    }


}
