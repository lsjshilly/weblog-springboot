package com.lsj.weblog.admin.service;

import com.lsj.weblog.admin.model.dto.PublishArticleReqDto;

public interface ArticleService {

    void createArticle(PublishArticleReqDto publishArticleReqDto);

}
