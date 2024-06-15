package com.lsj.weblog.security.handler;

import com.lsj.weblog.common.base.ResponseResult;
import com.lsj.weblog.common.utils.JsonUtil;
import com.lsj.weblog.common.utils.JwtUtil;
import com.lsj.weblog.common.utils.ResponseUtil;
import com.lsj.weblog.common.vo.UserVo;
import com.lsj.weblog.security.config.JwtTokenManagerProperties;
import com.lsj.weblog.security.domain.vo.UserAuth;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtTokenManagerProperties jwtTokenManagerProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userAuth, userVo);
        userVo.setPassword(null);
        // 生产jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("currentuser", JsonUtil.toJsonStr(userVo));
        String jwtToken = JwtUtil.generateToken(jwtTokenManagerProperties.getSecretKey(), jwtTokenManagerProperties.getExpires(), claims);
        userVo.setUsertoken(jwtToken);
        ResponseResult<UserVo> result = ResponseResult.success(userVo);
        ResponseUtil.ok(request, response, result);
    }
}
