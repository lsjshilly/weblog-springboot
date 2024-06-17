package com.lsj.weblog.security.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别 0 女 1 男
     */
    private Short sex;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}