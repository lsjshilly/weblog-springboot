package com.lsj.weblog.web.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客设置信息表
 *
 * @TableName tb_blog_setting
 */
@Data
public class BlogSetting implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 设置id
     */
    private Long id;
    /**
     * 博客名称
     */
    private String blogname;
    /**
     * 作者名称
     */
    private String authorname;
    /**
     * 作者头像
     */
    private String avatarUrl;
    /**
     * 博客描述
     */
    private String description;
    /**
     * github主页地址
     */
    private String githubHomePage;
    /**
     * gitee主页地址
     */
    private String giteeHomePage;
    /**
     * csdn主页地址
     */
    private String csdnHomePage;
    /**
     * 知乎主页地址
     */
    private String zhihuHomePage;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}