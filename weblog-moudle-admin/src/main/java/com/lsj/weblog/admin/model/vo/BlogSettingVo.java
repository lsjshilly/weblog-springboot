package com.lsj.weblog.admin.model.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogSettingVo {

    @ApiModelProperty(value = "博客名称")
    private String blogname;
    /**
     * 作者名称
     */
    @ApiModelProperty(value = "作者名称")
    private String authorname;
    /**
     * 作者头像
     */
    @ApiModelProperty(value = "作者头像")
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
