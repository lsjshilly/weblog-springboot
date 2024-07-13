package com.lsj.weblog.admin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@ApiModel(description = "更新文章请求Dto")
public class UpdateArticleReqDto {

    @ApiModelProperty(value = "文章id", required = true, example = "1")
    @NotNull(message = "文章id不能为空")
    private Long id;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题", required = true, example = "My First Blog Post")
    @NotBlank(message = "标题不能为空")
    @Size(min = 1, max = 100, message = "标题长度应在1到100个字符之间")
    private String title;

    /**
     * 文章封面
     */
    @ApiModelProperty(value = "文章封面URL", example = "https://example.com/cover.jpg")
    private String cover;

    /**
     * 文章摘要
     */
    @ApiModelProperty(value = "文章摘要", example = "This is a brief summary of the article.")
    @Size(max = 500, message = "摘要长度不应超过500个字符")
    private String summary;

    /**
     * 文章状态 0-草稿 1-已发布
     */
    @ApiModelProperty(value = "文章状态，0-草稿，1-已发布", required = true)
    private Integer status;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类ID", required = true, example = "1")
    @NotNull(message = "分类ID不能为空")
    private Long category;


    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容", required = true)
    @NotNull(message = "文章内容不能为空")
    private String content;

    /**
     * 文章标签
     */
    @ApiModelProperty(value = "标签", required = true)
    @NotEmpty(message = "标签不能为空")
    private List<String> tags;


}
