package com.lsj.weblog.web.domain.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateBlogSettingReqDto {

    @ApiModelProperty(value = "博客名称")
    @NotEmpty(message = "博客名称不能为空")
    private String blogname;
    /**
     * 作者名称
     */
    @ApiModelProperty(value = "作者名称")
    @NotEmpty(message = "作者名称不能为空")
    private String authorname;
    /**
     * 作者头像
     */
    @ApiModelProperty(value = "作者头像")
    @NotEmpty(message = "作者头像不能为空")
    private String avatarUrl;
    /**
     * 博客描述
     */
    @ApiModelProperty(value = "博客描述")
    private String description;
    /**
     * github主页地址
     */
    @ApiModelProperty(value = "github主页地址")
    private String githubHomePage;
    /**
     * gitee主页地址
     */
    @ApiModelProperty(value = "gitee主页地址")
    private String giteeHomePage;
    /**
     * csdn主页地址
     */
    @ApiModelProperty(value = "csdn主页地址")
    private String csdnHomePage;
    /**
     * 知乎主页地址
     */
    @ApiModelProperty(value = "知乎主页地址")
    private String zhihuHomePage;

}
