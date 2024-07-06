package com.lsj.weblog.admin.controller;


import com.lsj.weblog.admin.model.dto.AddCategoryReqDto;
import com.lsj.weblog.admin.model.dto.FindCategoryPageReqDto;
import com.lsj.weblog.admin.model.vo.CategoryVo;
import com.lsj.weblog.admin.service.CateGoryService;
import com.lsj.weblog.common.aspect.ApiOperationLog;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.common.base.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Api(tags = "分类管理接口")
@RequiredArgsConstructor
public class CategoryController {

    private final CateGoryService categoryService;


    @PostMapping("/category/add")
    @ApiOperationLog(description = "添加分类接口")
    @ApiOperation("添加分类接口")
    public ResponseResult<Void> addCategory(@RequestBody @Validated AddCategoryReqDto addCategoryReqDto) {
        categoryService.addCategory(addCategoryReqDto);
        return ResponseResult.success();
    }


    @DeleteMapping("/category/delete")
    @ApiOperationLog(description = "删除分类接口")
    @ApiOperation("添加分类接口")
    public ResponseResult<Void> deleteCategory(@RequestBody IdRequestDto idRequestDto) {
        categoryService.deleteCategory(idRequestDto);
        return ResponseResult.success();
    }


    @GetMapping("/category/page")
    @ApiOperationLog(description = "查询分类接口")
    @ApiOperation("查询分类接口")
    public ResponseResult<PageResult<CategoryVo>> getCategoryPage(FindCategoryPageReqDto findCategoryPageReqDto) {
        PageResult<CategoryVo> result = categoryService.findCategoryPage(findCategoryPageReqDto);
        return ResponseResult.success(result);
    }

    @GetMapping("/category/select/list")
    @ApiOperationLog(description = "查询分类下拉列表接口")
    @ApiOperation("查询分类下拉列表接口")
    public ResponseResult<List<CategoryVo>> listCategory() {
        List<CategoryVo> result = categoryService.listCategory();
        return ResponseResult.success(result);
    }
}
