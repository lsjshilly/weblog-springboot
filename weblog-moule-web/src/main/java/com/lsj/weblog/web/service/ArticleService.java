package com.lsj.weblog.web.service;


import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.web.domain.dto.FindArticleDetailReqDto;
import com.lsj.weblog.web.domain.dto.FindArticlePageReqDto;
import com.lsj.weblog.web.domain.dto.PublishArticleReqDto;
import com.lsj.weblog.web.domain.dto.UpdateArticleReqDto;
import com.lsj.weblog.web.domain.vo.ArticleVo;

public interface ArticleService {

    void createArticle(PublishArticleReqDto publishArticleReqDto);


    void deleteArticle(IdRequestDto idRequestDto);

    PageResult<ArticleVo> selectArticlePage(FindArticlePageReqDto findArticlePageReqDto);

    ArticleVo findArticleDetail(FindArticleDetailReqDto findArticleDetailReqDto);

    void updateArticle(UpdateArticleReqDto updateArticleReqDto);
}
