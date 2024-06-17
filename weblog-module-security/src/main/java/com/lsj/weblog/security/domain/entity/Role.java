package com.lsj.weblog.security.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName tb_role
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}