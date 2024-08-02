package com.lsj.weblog.web.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CategoryVo {

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
