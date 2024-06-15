package com.lsj.weblog.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsj.weblog.security.filter.JwtAuthenticationFilter;
import com.lsj.weblog.security.handler.JwtAuthFailHandler;
import com.lsj.weblog.security.handler.JwtAuthSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JwtAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private ObjectMapper mapper;

    @Resource
    private JwtAuthFailHandler jwtAuthFailHandler;

    @Resource
    private JwtAuthSuccessHandler JwtAuthSuccessHandler;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(mapper);
        jwtAuthenticationFilter.setAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class));
        jwtAuthenticationFilter.setAuthenticationFailureHandler(jwtAuthFailHandler);
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(JwtAuthSuccessHandler);
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
