package com.lsj.weblog.web.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsj.weblog.common.base.IdRequestDto;
import com.lsj.weblog.common.base.PageResult;
import com.lsj.weblog.common.execption.BizExecption;
import com.lsj.weblog.web.constant.CommonConst;
import com.lsj.weblog.web.domain.dto.*;
import com.lsj.weblog.web.domain.entity.*;
import com.lsj.weblog.web.domain.vo.ArticleVo;
import com.lsj.weblog.web.enums.ArticlePublishStatus;
import com.lsj.weblog.web.mapper.*;
import com.lsj.weblog.web.service.ArticleService;
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

        // 文章数据转换
        Article article = new Article();
        BeanUtils.copyProperties(publishArticleReqDto, article);

        // 校验分类是否存在及状态判断
        validateArticle(article);

        // 文章保存
        articleMapper.insert(article);

        ArticleDetail articleDetail = ArticleDetail.builder().articleId(article.getId()).content(publishArticleReqDto.getContent()).build();

        articleDetailMapper.insert(articleDetail);

        insertTags(publishArticleReqDto.getTags(), article.getId());

    }

    @Override
    public void updateArticle(UpdateArticleReqDto updateArticleReqDto) {

        // 文章数据转换
        Article article = new Article();
        BeanUtils.copyProperties(updateArticleReqDto, article);
        article.setId(updateArticleReqDto.getId());

        // 校验文章是否存在
        Article existArticle = articleMapper.selectById(article.getId());
        if (existArticle == null) {
            throw new BizExecption(ARTICLE_NOT_EXIST_ERROR);
        }
        // 文章状态判断及状态判断
        validateArticle(article);

        // 文章保存
        articleMapper.updateById(article);

        ArticleDetail articleDetail = ArticleDetail.builder().articleId(article.getId()).content(updateArticleReqDto.getContent()).build();

        articleDetailMapper.updateByArticleId(articleDetail);
        // 先删除文章保存的标签关系，再更新
        articleTagMapper.deleteByArticleId(article.getId());
        insertTags(updateArticleReqDto.getTags(), article.getId());
    }

    private void validateArticle(Article article) {
        if (article == null) {
            throw new BizExecption(ARTICLE_NOT_EXIST_ERROR);
        }
        // 校验分类是否存在
        Category category = categoryMapper.selectById(article.getCategory());
        if (category == null) {
            throw new BizExecption(CATEGORY_NOT_EXIST_ERROR);
        }

        if (article.getStatus() != ArticlePublishStatus.DRAFIT.getCode() && article.getStatus() != ArticlePublishStatus.PUBLISH.getCode()) {
            throw new BizExecption(ARTICLE_STATUS_ERROR);
        }
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
        return getArticleVoPageResult(findArticlePageReqDto);
    }

    @Override
    public ArticleVo findArticleDetail(FindArticleDetailReqDto findArticleDetailReqDto) {
        return articleMapper.findDetail(findArticleDetailReqDto);

    }


    @Override
    public PageResult<ArticleVo> findArticlePageList(WebFindArticlePageReqDto webFindArticlePageReqDto) {

        FindArticlePageReqDto findArticlePageReqDto = new FindArticlePageReqDto();
        findArticlePageReqDto.setPageNum(webFindArticlePageReqDto.getPageNum());
        findArticlePageReqDto.setPageSize(webFindArticlePageReqDto.getPageSize());

        return getArticleVoPageResult(findArticlePageReqDto);

    }

    private PageResult<ArticleVo> getArticleVoPageResult(FindArticlePageReqDto findArticlePageReqDto) {
        if (findArticlePageReqDto.getPageSize() > CommonConst.MAX_PAGE_SIZE) {
            findArticlePageReqDto.setPageSize(CommonConst.MAX_PAGE_SIZE);
        }

        if (findArticlePageReqDto.getPageSize() < CommonConst.MIN_PAGE_SIZE) {
            findArticlePageReqDto.setPageSize(CommonConst.DEFAULT_PAGE_SIZE);
        }

        if (findArticlePageReqDto.getPageNum() < CommonConst.DEFAULT_PAGE_NUM) {
            findArticlePageReqDto.setPageNum(CommonConst.DEFAULT_PAGE_NUM);
        }

        PageHelper.startPage(findArticlePageReqDto.getPageNum(), findArticlePageReqDto.getPageSize());
        Page<ArticleVo> articleVos = articleMapper.selectPage(findArticlePageReqDto);
        return PageResult.<ArticleVo>builder()
                .total(articleVos.getTotal())
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

        insertBatchService.insertBatch(notExistTags, "com.lsj.weblog.web.mapper.TagMapper.insert");

        List<ArticleTag> articleTagRels = new ArrayList<>();
        for (String existTagName : existTagNamess) {
            ArticleTag articleTagRel = ArticleTag.builder().articleId(articleId).tagId(tagNameIdMap.get(existTagName.toLowerCase(Locale.ROOT))).build();
            articleTagRels.add(articleTagRel);
        }

        for (Tag notExistTag : notExistTags) {
            ArticleTag articleTagRel = ArticleTag.builder().articleId(articleId).tagId(notExistTag.getId()).build();
            articleTagRels.add(articleTagRel);
        }

        insertBatchService.insertBatch(articleTagRels, "com.lsj.weblog.web.mapper.ArticleTagMapper.insert");
    }
}
