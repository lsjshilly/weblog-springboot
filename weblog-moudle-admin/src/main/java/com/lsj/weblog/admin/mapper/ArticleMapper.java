package com.lsj.weblog.admin.mapper;

import com.lsj.weblog.admin.model.entity.Article;

/**
 * @author liushijie
 * @description 针对表【tb_article(文章表)】的数据库操作Mapper
 * @createDate 2024-07-10 22:02:07
 * @Entity com.lsj.weblog.admin.model.entity.Article
 */
public interface ArticleMapper {

    int deleteById(Long id);

    int insert(Article record);

    Article selectByPrimaryKey(Long id);

    int updateById(Article record);


}
