package com.lsj.weblog.admin.mapper;

import com.lsj.weblog.admin.model.entity.ArticleTag;
import org.apache.ibatis.annotations.Delete;

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


    @Delete("delete from tb_article_tag where article_id = #{articleId}")
    int deleteByArticleId(long articleId);

    int selectCountByTagId(Long id);
}
