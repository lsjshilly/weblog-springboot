package com.lsj.weblog.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章内容表
 *
 * @TableName tb_article_detail
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 文章内容id
     */
    private Long id;
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 文章内容
     */
    private String content;
}