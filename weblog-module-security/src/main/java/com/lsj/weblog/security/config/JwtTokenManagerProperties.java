package com.lsj.weblog.security.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "weblog.security.jwt")
public class JwtTokenManagerProperties {

    private String secretKey;

    private long expires;
    
}
