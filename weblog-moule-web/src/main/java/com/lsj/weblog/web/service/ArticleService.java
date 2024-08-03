package com.lsj.weblog.web.service;


import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.web.domain.dto.*;
import com.lsj.weblog.web.domain.vo.ArticleVo;

public interface ArticleService {

    void createArticle(PublishArticleReqDto publishArticleReqDto);

    void updateArticle(UpdateArticleReqDto updateArticleReqDto);

    void deleteArticle(IdRequestDto idRequestDto);

    PageResult<ArticleVo> selectArticlePage(FindArticlePageReqDto findArticlePageReqDto);

    ArticleVo findArticleDetail(FindArticleDetailReqDto findArticleDetailReqDto);


    PageResult<ArticleVo> findArticlePageList(WebFindArticlePageReqDto webFindArticlePageReqDto);
}
