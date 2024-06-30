package com.lsj.weblog.common.base;


import lombok.Data;

@Data
public class BasePageQuery {

    private Integer pageNum = 1;

    private Integer pageSize = 10;


}
