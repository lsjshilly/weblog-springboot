package com.lsj.weblog.common.execption;


import com.lsj.weblog.common.base.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.SYSTEM_ERROR;
import static com.lsj.weblog.common.execption.ResponseCodeEnum.VALIDATION_ERROR;

@RestControllerAdvice
@Slf4j
public class GlobalExecptionHandler {


    @ExceptionHandler(BizExecption.class)
    public ResponseResult<Void> handlerBizExecption(BizExecption e, HttpServletRequest request) {
        log.error("bizExecption handler: request:{}, errCode:{}, errMsg:{}", request.getRequestURI(), e.getCode(), e.getMessage());
        return new ResponseResult<>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> handlerOtherExecption(Exception e, HttpServletRequest request) {
        log.error("bizExecption handler: request:{},  errMsg:{}", request.getRequestURI(), e.getMessage());
        return new ResponseResult<>(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Void> handlerValidationExecption(MethodArgumentNotValidException e, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BindingResult bindingResult = e.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append(" ").append(fieldError.getDefaultMessage()).append(";");
        }
        log.error("methodArgumentNotValidException handler: request:{},  details:{}", request.getRequestURI(), sb);
        return new ResponseResult<>(VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getMessage(), sb.toString(), null);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult<Void> handlerAccessDenied(AccessDeniedException e, HttpServletRequest request) {
        log.error("accessDeniedException handler: request:{},  errMsg:{}", request.getRequestURI(), e.getMessage());
        throw e;
    }

}
