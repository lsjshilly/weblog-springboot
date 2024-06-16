package com.lsj.weblog.security.handler;

import com.lsj.weblog.common.base.ResponseResult;
import com.lsj.weblog.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.UNAUTHORIZED_ERROR;

@Component
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn("==============AuthenticationException", authException);
        if (authException instanceof InsufficientAuthenticationException) {
            ResponseUtil.fail(request, response, ResponseResult.error(UNAUTHORIZED_ERROR));
            return;
        }

        ResponseUtil.fail(request, response, ResponseResult.error(UNAUTHORIZED_ERROR.getCode(), authException.getMessage()));

    }
}
