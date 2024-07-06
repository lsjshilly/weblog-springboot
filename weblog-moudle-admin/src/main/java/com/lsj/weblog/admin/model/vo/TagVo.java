package com.lsj.weblog.admin.model.vo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class TagVo {

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

}
