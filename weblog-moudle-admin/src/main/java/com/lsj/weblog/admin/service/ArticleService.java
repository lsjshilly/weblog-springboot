package com.lsj.weblog.admin.service;

import com.lsj.weblog.admin.model.dto.FindArticlePageReqDto;
import com.lsj.weblog.admin.model.dto.PublishArticleReqDto;
import com.lsj.weblog.admin.model.vo.ArticleVo;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;

public interface ArticleService {

    void createArticle(PublishArticleReqDto publishArticleReqDto);


    void deleteArticle(IdRequestDto idRequestDto);

    PageResult<ArticleVo> selectArticlePage(FindArticlePageReqDto findArticlePageReqDto);
}
