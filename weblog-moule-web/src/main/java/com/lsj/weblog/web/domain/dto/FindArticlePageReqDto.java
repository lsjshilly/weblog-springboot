package com.lsj.weblog.web.domain.dto;


import com.lsj.weblog.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FindArticlePageReqDto extends BasePageQuery {

    /**
     * 实体的唯一标识符。
     */
    private Long id;

    /**
     * 标签Id集合
     */
    private List<Long> tagIds;

    /**
     * 分类Id
     */
    private Long categoryId;

    /**
     * 文章状态：0-草稿，1-发布
     */
    private Integer status;

    /**
     * 实体的名称。
     */
    private String title;

    /**
     * 实体活动的开始日期。
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /**
     * 实体活动的结束日期。
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


}
