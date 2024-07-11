package com.lsj.weblog.admin.controller;


import com.lsj.weblog.admin.model.dto.FindArticlePageReqDto;
import com.lsj.weblog.admin.model.dto.PublishArticleReqDto;
import com.lsj.weblog.admin.model.vo.ArticleVo;
import com.lsj.weblog.admin.service.ArticleService;
import com.lsj.weblog.common.aspect.ApiOperationLog;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.common.base.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/article")
@RequiredArgsConstructor
@Api(tags = "文章管理接口")
public class ArticleController {

    private final ArticleService articleService;


    @PostMapping("/publish")
    @ApiOperationLog(description = "发布文章接口")
    @ApiOperation("发布文章接口")
    public ResponseResult<Void> publish(@RequestBody @Validated PublishArticleReqDto publishArticleReqDto) {

        articleService.createArticle(publishArticleReqDto);

        return ResponseResult.success();

    }


    @DeleteMapping()
    @ApiOperationLog(description = "删除文章接口")
    @ApiOperation("删除文章接口")
    public ResponseResult<Void> deleteArticle(@RequestBody IdRequestDto idRequestDto) {

        articleService.deleteArticle(idRequestDto);

        return ResponseResult.success();

    }

    @GetMapping()
    @ApiOperationLog(description = "分页查询文章接口")
    @ApiOperation("分页查询文章接口")
    public ResponseResult<PageResult<ArticleVo>> deleteArticle(FindArticlePageReqDto findArticlePageReqDto) {

        PageResult<ArticleVo> pageResult = articleService.selectArticlePage(findArticlePageReqDto);

        return ResponseResult.success(pageResult);

    }


}