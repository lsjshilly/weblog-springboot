package com.lsj.weblog.security.handler;

import com.lsj.weblog.common.base.ResponseResult;
import com.lsj.weblog.common.utils.ResponseUtil;
import com.lsj.weblog.security.execption.UsernameOrPasswordNullExecption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.LOGIN_ERROR;
import static com.lsj.weblog.common.execption.ResponseCodeEnum.LOGIN_USERNAME_OR_PASSWORD_ERROR;

@Service
@Slf4j
public class JwtAuthFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.warn("AuthenticationException: ", exception);

        if (exception instanceof UsernameOrPasswordNullExecption ue) {
            ResponseUtil.fail(request, response, ResponseResult.error(LOGIN_ERROR.getCode(), ue.getMessage()));
        } else if (exception instanceof BadCredentialsException) {
            ResponseUtil.fail(request, response, ResponseResult.error(LOGIN_USERNAME_OR_PASSWORD_ERROR));
        }
        
        ResponseUtil.fail(request, response, ResponseResult.error(LOGIN_ERROR));
    }
}
