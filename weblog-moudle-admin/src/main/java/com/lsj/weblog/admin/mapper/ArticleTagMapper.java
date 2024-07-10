package com.lsj.weblog.admin.mapper;

import com.lsj.weblog.admin.model.entity.ArticleTag;

/**
 * @author liushijie
 * @description 针对表【tb_article_tag(文章标签表)】的数据库操作Mapper
 * @createDate 2024-07-10 22:02:07
 * @Entity com.lsj.weblog.admin.model.entity.ArticleTag
 */
public interface ArticleTagMapper {

    int deleteById(Long id);

    int insert(ArticleTag record);

    ArticleTag selectById(Long id);

    int updateById(ArticleTag record);


}
