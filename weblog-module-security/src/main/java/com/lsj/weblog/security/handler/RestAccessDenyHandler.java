package com.lsj.weblog.security.handler;

import com.lsj.weblog.common.base.ResponseResult;
import com.lsj.weblog.common.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lsj.weblog.common.execption.ResponseCodeEnum.IN_SUFFICIENT_PERMISSION_ERROR;

@Component
public class RestAccessDenyHandler implements AccessDeniedHandler {
    private static final Logger log = LoggerFactory.getLogger(RestAccessDenyHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("accessDeniedException:{}", accessDeniedException.getMessage());
        ResponseUtil.fail(request, response, ResponseResult.error(IN_SUFFICIENT_PERMISSION_ERROR));
    }
}
