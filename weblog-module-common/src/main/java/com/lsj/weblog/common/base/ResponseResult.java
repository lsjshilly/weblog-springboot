package com.lsj.weblog.common.base;

import com.lsj.weblog.common.execption.BaseExecptionInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.SUCCESS_ERROR;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    // 响应代码
    private String code;

    // 响应消息
    private String message;

    // 详细信息
    private String details;

    // 响应数据
    private T data;

    // 构造函数，仅包含代码和消息
    public ResponseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 构造函数，包含代码、消息和数据
    public ResponseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应，不包含数据
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(SUCCESS_ERROR.getCode(), SUCCESS_ERROR.getMessage());
    }

    // 成功响应，包含数据
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(SUCCESS_ERROR.getCode(), SUCCESS_ERROR.getMessage(), data);
    }

    // 错误响应，不包含详细信息
    public static <T> ResponseResult<T> error(String code, String message) {
        return new ResponseResult<>(code, message);
    }

    // 错误响应，不包含详细信息
    public static <T> ResponseResult<T> error(String code, String message, String details) {
        return new ResponseResult<>(code, message, details, null);
    }


    // 错误响应，不包含详细信息
    public static <T> ResponseResult<T> error(BaseExecptionInterface execptionInterface) {
        return new ResponseResult<>(execptionInterface.getCode(), execptionInterface.getMessage());
    }

    // 错误响应，包含详细信息
    public static <T> ResponseResult<T> error(BaseExecptionInterface execptionInterface, String details) {
        return new ResponseResult<>(execptionInterface.getCode(), execptionInterface.getMessage(), details, null);
    }
}
