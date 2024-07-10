package com.lsj.weblog.admin.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章表
 *
 * @TableName tb_article
 */
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * 文章状态 0-草稿 1-已发布
     */
    private Integer status;
    /**
     * 分类id
     */
    private Long category;
    /**
     * 阅读量
     */
    private Integer readNum;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Integer isDeleted;
}