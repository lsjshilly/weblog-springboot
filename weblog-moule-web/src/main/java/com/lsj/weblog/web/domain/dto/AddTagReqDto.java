package com.lsj.weblog.web.domain.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class AddTagReqDto {

    @NotEmpty(message = "标签名称不能为空")
    private List<String> tags;

}
