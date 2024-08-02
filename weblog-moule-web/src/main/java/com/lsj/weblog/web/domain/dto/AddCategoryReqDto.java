package com.lsj.weblog.web.domain.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "添加分类请求参数")
public class AddCategoryReqDto {

    @NotBlank(message = "分类名称不能为空")
    @Length(max = 10, min = 1, message = "分类名称长度在1-10之间")
    private String name;

}
