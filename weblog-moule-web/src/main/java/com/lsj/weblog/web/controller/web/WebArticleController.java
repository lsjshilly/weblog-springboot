package com.lsj.weblog.web.controller.web;


import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.common.base.ResponseResult;
import com.lsj.weblog.web.domain.dto.WebFindArticlePageReqDto;
import com.lsj.weblog.web.domain.vo.ArticleVo;
import com.lsj.weblog.web.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/article")
@RequiredArgsConstructor
public class WebArticleController {

    private final ArticleService articleService;


    @GetMapping("/list")
    public ResponseResult<PageResult<ArticleVo>> listArticle(WebFindArticlePageReqDto webFindArticlePageReqDto) {

        PageResult<ArticleVo> articleVoPageResult = articleService.findArticlePageList(webFindArticlePageReqDto);
        return ResponseResult.success(articleVoPageResult);

    }

}
