package com.lsj.weblog.admin.service.impl;

import com.github.pagehelper.Page;
import com.lsj.weblog.admin.enums.ArticlePublishStatus;
import com.lsj.weblog.admin.mapper.*;
import com.lsj.weblog.admin.model.dto.FindArticlePageReqDto;
import com.lsj.weblog.admin.model.dto.PublishArticleReqDto;
import com.lsj.weblog.admin.model.entity.*;
import com.lsj.weblog.admin.model.vo.ArticleVo;
import com.lsj.weblog.admin.service.ArticleService;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.common.execption.BizExecption;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.*;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final CategoryMapper categoryMapper;

    private final ArticleMapper articleMapper;

    private final ArticleDetailMapper articleDetailMapper;

    private final TagMapper tagMapper;


    private final InsertBatchService insertBatchService;
    private final ArticleTagMapper articleTagMapper;


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

        ArticleDetail articleDetail = ArticleDetail.builder().articleId(article.getId()).content(publishArticleReqDto.getContent()).build();

        articleDetailMapper.insert(articleDetail);


        insertTags(publishArticleReqDto.getTags(), article.getId());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(IdRequestDto idRequestDto) {
        if (idRequestDto == null || idRequestDto.getId() == null) {
            throw new BizExecption(VALIDATION_ERROR);
        }
        // 这里是逻辑删除，所以文章内容就不再删除了
        articleMapper.deleteById(idRequestDto.getId());

        articleTagMapper.deleteByArticleId(idRequestDto.getId());

    }

    @Override
    public PageResult<ArticleVo> selectArticlePage(FindArticlePageReqDto findArticlePageReqDto) {

        Page<ArticleVo> articleVos = articleMapper.selectPage(findArticlePageReqDto);

        return PageResult.<ArticleVo>builder().
                total(articleVos.getTotal())
                .currentPage(articleVos.getPageNum())
                .pageSize(articleVos.getPageSize())
                .items(articleVos.getResult())
                .build();
    }

    private void insertTags(List<String> tags, long articleId) {
        List<Tag> tagList = tagMapper.selectList();
        Map<String, Long> tagNameIdMap = tagList.stream().collect(Collectors.toMap(tag -> tag.getName().toLowerCase(Locale.ROOT), Tag::getId));

        List<String> existTagNamess = tags.stream().filter(StringUtils::isNoneEmpty).filter(tag -> tagNameIdMap.containsKey(tag.toLowerCase(Locale.ROOT))).toList();
        List<String> notExistTagBNamess = tags.stream().filter(StringUtils::isNoneEmpty).filter(tag -> !tagNameIdMap.containsKey(tag.toLowerCase(Locale.ROOT))).toList();
        List<Tag> notExistTags = new ArrayList<>();
        for (String notExistTagName : notExistTagBNamess) {
            Tag tag = new Tag();
            tag.setName(Strings.toUpperCase(notExistTagName.substring(0, 1)) + notExistTagName.substring(1));
            notExistTags.add(tag);
        }

        insertBatchService.insertBatch(notExistTags, "com.lsj.weblog.admin.mapper.TagMapper.insert");

        List<ArticleTag> articleTagRels = new ArrayList<>();
        for (String existTagName : existTagNamess) {
            ArticleTag articleTagRel = ArticleTag.builder().articleId(articleId).tagId(tagNameIdMap.get(existTagName.toLowerCase(Locale.ROOT))).build();
            articleTagRels.add(articleTagRel);
        }

        for (Tag notExistTag : notExistTags) {
            ArticleTag articleTagRel = ArticleTag.builder().articleId(articleId).tagId(notExistTag.getId()).build();
            articleTagRels.add(articleTagRel);
        }

        insertBatchService.insertBatch(articleTagRels, "com.lsj.weblog.admin.mapper.ArticleTagMapper.insert");
    }
}
