package com.lsj.weblog.admin.service.impl;

import com.lsj.weblog.admin.enums.ArticlePublishStatus;
import com.lsj.weblog.admin.mapper.ArticleDetailMapper;
import com.lsj.weblog.admin.mapper.ArticleMapper;
import com.lsj.weblog.admin.mapper.CategoryMapper;
import com.lsj.weblog.admin.model.dto.PublishArticleReqDto;
import com.lsj.weblog.admin.model.entity.Article;
import com.lsj.weblog.admin.model.entity.ArticleDetail;
import com.lsj.weblog.admin.model.entity.ArticleTag;
import com.lsj.weblog.admin.model.entity.Category;
import com.lsj.weblog.admin.service.ArticleService;
import com.lsj.weblog.common.execption.BizExecption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.ARTICLE_STATUS_ERROR;
import static com.lsj.weblog.common.execption.ResponseCodeEnum.CATEGORY_NOT_EXIST_ERROR;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final CategoryMapper categoryMapper;

    private final ArticleMapper articleMapper;

    private final ArticleDetailMapper articleDetailMapper;

    private final InsertBatchService<ArticleTag> insertBatchService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createArticle(PublishArticleReqDto publishArticleReqDto) {

        // 校验分类是否存在
        Category category = categoryMapper.selectById(publishArticleReqDto.getCategory());
        if (category == null) {
            throw new BizExecption(CATEGORY_NOT_EXIST_ERROR);
        }
        // 文章状态判断
        if (publishArticleReqDto.getStatus() != ArticlePublishStatus.DRAFIT.getCode() && publishArticleReqDto.getStatus() != ArticlePublishStatus.PUBLISH.getCode()) {
            throw new BizExecption(ARTICLE_STATUS_ERROR);
        }

        // 文章保存
        Article article = new Article();
        BeanUtils.copyProperties(publishArticleReqDto, article);
        articleMapper.insert(article);

        ArticleDetail articleDetail = ArticleDetail.builder()
                .articleId(article.getId())
                .content(publishArticleReqDto.getContent())
                .build();

        articleDetailMapper.insert(articleDetail);


        insertTags(publishArticleReqDto.getTags());

    }

    private void insertTags(List<String> tags) {

    }
}
