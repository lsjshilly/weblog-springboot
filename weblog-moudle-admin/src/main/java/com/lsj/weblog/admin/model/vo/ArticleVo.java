package com.lsj.weblog.admin.model.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleVo {
    /**
     * 文章id
     */
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章封面
     */
    private String cover;
    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章状态 0-草稿 1-已发布
     */
    private Integer status;
    /**
     * 分类id
     */
    private Long category;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 标签
     */
    private List<TagVo> tags;

    /**
     * 阅读量
     */
    private Integer readNum;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
