package com.lsj.weblog.web.mapper;

import com.lsj.weblog.web.domain.entity.ArticleDetail;

/**
 * @author liushijie
 * @description 针对表【tb_article_detail(文章内容表)】的数据库操作Mapper
 * @createDate 2024-07-10 22:02:07
 * @Entity com.lsj.weblog.admin.model.entity.ArticleDetail
 */
public interface ArticleDetailMapper {

    int deleteById(Long id);

    int insert(ArticleDetail record);

    ArticleDetail selectId(Long id);

    int updateById(ArticleDetail record);

    int updateByArticleId(ArticleDetail record);
}
