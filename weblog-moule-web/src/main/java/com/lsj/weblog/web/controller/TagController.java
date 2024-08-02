package com.lsj.weblog.web.controller;


import com.lsj.weblog.common.aspect.ApiOperationLog;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.common.base.ResponseResult;
import com.lsj.weblog.web.domain.dto.AddTagReqDto;
import com.lsj.weblog.web.domain.dto.FindTagPageReqDto;
import com.lsj.weblog.web.domain.dto.SearchTagReqDto;
import com.lsj.weblog.web.domain.vo.TagVo;
import com.lsj.weblog.web.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Api(tags = "标签管理接口")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;


    @PostMapping("/tag/add")
    @ApiOperationLog(description = "添加标签接口")
    @ApiOperation("添加标签接口")
    public ResponseResult<Void> addTag(@RequestBody @Validated AddTagReqDto addTagReqDto) {
        tagService.addTag(addTagReqDto);
        return ResponseResult.success();
    }


    @DeleteMapping("/tag/delete")
    @ApiOperationLog(description = "删除标签接口")
    @ApiOperation("删除标签接口")
    public ResponseResult<Void> deleteTag(@RequestBody IdRequestDto idRequestDto) {
        tagService.deleteTag(idRequestDto);
        return ResponseResult.success();
    }


    @GetMapping("/tag/page")
    @ApiOperationLog(description = "分页查询标签接口")
    @ApiOperation("分页查询标签接口")
    public ResponseResult<PageResult<TagVo>> getTagPage(FindTagPageReqDto findTagPageReqDto) {
        PageResult<TagVo> result = tagService.findTagPage(findTagPageReqDto);
        return ResponseResult.success(result);
    }

    @GetMapping("/tag/select/list")
    @ApiOperationLog(description = "查询标签下拉列表接口")
    @ApiOperation("查询标签下拉列表接口")
    public ResponseResult<List<TagVo>> searchTags(SearchTagReqDto searchTagReqDto) {
        List<TagVo> result = tagService.searchTags(searchTagReqDto);
        return ResponseResult.success(result);
    }
}
