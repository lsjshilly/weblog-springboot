package com.lsj.weblog.admin.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章标签表
 *
 * @TableName tb_article_tag
 */
@Data
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 文章标签id
     */
    private Long id;
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 标签id
     */
    private Long tagId;
}