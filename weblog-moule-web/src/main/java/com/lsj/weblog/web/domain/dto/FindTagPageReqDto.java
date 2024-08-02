package com.lsj.weblog.web.domain.dto;


import com.lsj.weblog.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class FindTagPageReqDto extends BasePageQuery {

    /**
     * 实体的唯一标识符。
     */
    private Long id;

    /**
     * 实体的名称。
     */
    private String name;

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
