package com.lsj.weblog.security.filter;

import com.lsj.weblog.common.utils.JsonUtil;
import com.lsj.weblog.common.utils.JwtUtil;
import com.lsj.weblog.common.vo.UserVo;
import com.lsj.weblog.security.config.JwtTokenManagerProperties;
import com.lsj.weblog.security.domain.vo.UserAuth;
import com.lsj.weblog.security.handler.RestAuthenticationEntryPoint;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static final int BEAARER_SPLIT_LENGTH = 2;

    @Resource
    private JwtTokenManagerProperties jwtTokenManagerProperties;


    @Resource
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (StringUtils.startsWith(authorization, "Bearer") && StringUtils.split(authorization).length == BEAARER_SPLIT_LENGTH) {
            String jwtToken = StringUtils.split(authorization)[1];
            Claims claims;
            try {
                claims = JwtUtil.parseToken(jwtTokenManagerProperties.getSecretKey(), jwtToken);
            } catch (ExpiredJwtException e) {
                restAuthenticationEntryPoint.commence(request, response, new AuthenticationServiceException("TOKEN 已过期"));
                return;
            } catch (JwtException | IllegalArgumentException e) {
                restAuthenticationEntryPoint.commence(request, response, new AuthenticationServiceException("TOKEN 无效"));
                return;
            }

            String currentuser = claims.get("currentuser", String.class);
            if (StringUtils.isNoneEmpty(currentuser)) {
                UserVo userVo = JsonUtil.parse(currentuser, UserVo.class);
                if (userVo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserAuth userAuth = new UserAuth(userVo);
                    UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(userAuth, null, userAuth.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticated);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
