package com.lsj.weblog.admin.mapper;

import com.github.pagehelper.Page;
import com.lsj.weblog.admin.model.dto.FindArticleDetailReqDto;
import com.lsj.weblog.admin.model.dto.FindArticlePageReqDto;
import com.lsj.weblog.admin.model.entity.Article;
import com.lsj.weblog.admin.model.vo.ArticleVo;

/**
 * @author liushijie
 * @description 针对表【tb_article(文章表)】的数据库操作Mapper
 * @createDate 2024-07-10 22:02:07
 * @Entity com.lsj.weblog.admin.model.entity.Article
 */
public interface ArticleMapper {

    int deleteById(Long id);

    int insert(Article record);

    Article selectById(Long id);

    int updateById(Article record);


    Page<ArticleVo> selectPage(FindArticlePageReqDto findArticlePageReqDto);

    ArticleVo findDetail(FindArticleDetailReqDto findArticleDetailReqDto);

    int selectCountByCategoryId(Long categoryId);
}
