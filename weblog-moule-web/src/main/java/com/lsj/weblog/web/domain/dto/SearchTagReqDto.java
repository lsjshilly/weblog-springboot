package com.lsj.weblog.web.domain.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchTagReqDto {

    @ApiModelProperty("标签名称")
    private String name;

}
