package com.lsj.weblog.security.config;


import com.lsj.weblog.security.filter.TokenAuthenticationFilter;
import com.lsj.weblog.security.handler.RestAccessDenyHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableMethodSecurity
@EnableConfigurationProperties(JwtTokenManagerProperties.class)
public class SecurityConfig {


    @Resource
    private JwtAuthenticationSecurityConfig authenticationSecurityConfig;

    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;


    @Resource
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Resource
    private RestAccessDenyHandler restAccessDenyHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().disable()
                .apply(authenticationSecurityConfig)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(restAccessDenyHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
