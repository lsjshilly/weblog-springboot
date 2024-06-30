package com.lsj.weblog.common.base;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResult<T> {

    private Long total;

    private Integer pageSize;

    private Integer currentPage;

    private List<T> items;


}
