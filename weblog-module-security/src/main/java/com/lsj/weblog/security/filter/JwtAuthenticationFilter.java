package com.lsj.weblog.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsj.weblog.security.domain.dto.UserLoginDto;
import com.lsj.weblog.security.execption.UsernameOrPasswordNullExecption;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login", "POST");
    private final ObjectMapper mapper;

    public JwtAuthenticationFilter(ObjectMapper mapper) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.mapper = mapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {

            UserLoginDto userLoginDto = mapper.readValue(request.getInputStream(), UserLoginDto.class);
            if (userLoginDto == null || StringUtils.isAnyEmpty(userLoginDto.getUsername(), userLoginDto.getPassword())) {
                throw new UsernameOrPasswordNullExecption("用户名或密码不能为空");
            }

            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(userLoginDto.getUsername(), userLoginDto.getPassword());
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


}
