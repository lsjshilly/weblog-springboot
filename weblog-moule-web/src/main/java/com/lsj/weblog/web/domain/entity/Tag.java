package com.lsj.weblog.web.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 标签表
 *
 * @TableName tb_tag
 */
@Data
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 标签id
     */
    private Long id;
    /**
     * 标签名称
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