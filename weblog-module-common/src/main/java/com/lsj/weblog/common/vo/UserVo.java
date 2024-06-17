package com.lsj.weblog.common.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class UserVo {

    /**
     * 用户实体类，用于表示系统中的用户信息。
     */

    private Long id; // 用户ID

    private String username; // 用户名

    private String password; // 密码

    private String nickname; // 昵称

    private String realname; // 真实姓名

    private String phone; // 电话号码

    private short sex; // 性别

    private List<RoleVo> roleVoList = new ArrayList<>(); // 用户角色列表

    private LocalDateTime createTime; // 用户创建时间

    private String usertoken;
}
