package com.lsj.weblog.common.execption;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCodeEnum implements BaseExecptionInterface {

    // ----------- 通用异常状态码 -----------

    SUCCESS_ERROR("000000", "成功"),

    SYSTEM_ERROR("100001", "出错啦，后台小哥正在努力修复中..."),

    PRODUCT_NOT_FOUND("100002", "该产品不存在（测试使用）"),

    VALIDATION_ERROR("100003", "参数校验错误"),


    // -----------用户相关异常-----------
    LOGIN_ERROR("100101", "登录失败"),

    LOGIN_USERNAME_OR_PASSWORD_ERROR("100102", "用户名或密码错误"),

    UNAUTHORIZED_ERROR("100103", "无权限访问，请先登录"),

    IN_SUFFICIENT_PERMISSION_ERROR("100104", "权限不足，无法访问"),

    USER_NOT_EXIST_ERROR("100105", "用户不存在"),

    PASSWORD_REPAT_ERROR("100106", "密码不允许设置重复"),


    // -----------后台管理 分类相关异常----------
    CATEGORY_NAME_EXIST_ERROR("100201", "分类名称已存在，请勿重复添加"),

    TAG_NAME_EXIST_ERROR("100202", "标签名称已存在，请勿重复添加"),

    AVATAR_EMPTY_ERROR("100203", "头像文件不能为空"),

    AVATAR_UPLOAD_ERROR("100204", "头像上传失败"),

    CATEGORY_NOT_EXIST_ERROR("100205", "分类名称不存在"),

    ARTICLE_STATUS_ERROR("100206", "文章只能为发布或草稿状态"),


    ;


    private String code;
    private String message;


}
