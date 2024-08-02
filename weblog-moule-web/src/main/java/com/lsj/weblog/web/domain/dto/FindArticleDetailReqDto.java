package com.lsj.weblog.web.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("查询文章详情dto")
public class FindArticleDetailReqDto {

    @ApiModelProperty("文章id")
    @NotNull(message = "文章id不能为空")
    private Long id;

}
