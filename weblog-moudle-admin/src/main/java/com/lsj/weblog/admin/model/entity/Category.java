package com.lsj.weblog.admin.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 分类表
 *
 * @TableName tb_category
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 分类id
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}