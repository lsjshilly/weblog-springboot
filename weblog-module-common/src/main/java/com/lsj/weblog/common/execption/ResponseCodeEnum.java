package com.lsj.weblog.common.execption;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCodeEnum implements BaseExecptionInterface {

    // ----------- 通用异常状态码 -----------

    SUCCESS_ERROR("000000", "成功"),

    SYSTEM_ERROR("100001", "出错啦，后台小哥正在努力修复中..."),

    // ----------- 业务异常状态码 -----------
    PRODUCT_NOT_FOUND("100002", "该产品不存在（测试使用）"),

    VALIDATION_ERROR("100003", "参数校验错误"),


    LOGIN_ERROR("100101", "登录失败"),

    LOGIN_USERNAME_OR_PASSWORD_ERROR("100102", "用户名或密码错误"),
    ;


    private String code;
    private String message;


}
