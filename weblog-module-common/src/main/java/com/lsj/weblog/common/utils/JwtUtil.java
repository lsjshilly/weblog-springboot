package com.lsj.weblog.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    /**
     * JWT令牌的发行者。
     */
    private static final String ISSUER = "weblog";

    /**
     * 根据指定的密钥、过期时间和声明生成JWT令牌。
     *
     * @param secret     用于签名JWT的密钥，需要先进行Base64解码。
     * @param expireTime JWT的过期时间，以秒为单位。
     * @param claims     JWT中包含的自定义声明。
     * @return 生成的JWT令牌字符串。
     */
    public static String generateToken(String secret, long expireTime, Map<String, Object> claims) {
        // 根据密钥字符串生成HMAC SHA-256密钥
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        // 获取当前时间的Instant对象
        Instant instant = new Date().toInstant();
        // 计算JWT的过期时间
        Date expireDate = Date.from(instant.plusSeconds(expireTime));

        // 使用JJWT库构建JWT令牌，设置发行者、过期时间、签名密钥和自定义声明，最后生成紧凑型JWT字符串
        return Jwts.builder()
                .issuer(ISSUER)
                .expiration(expireDate)
                .signWith(key)
                .claims(claims)
                .compact();
    }

    /**
     * 解析JWT令牌，验证其签名并返回其中的声明。
     *
     * @param secret 用于验证JWT签名的密钥，需要先进行Base64解码。
     * @param token  待解析的JWT令牌字符串。
     * @return JWT令牌中的声明部分。
     */
    public static Claims parseToken(String secret, String token) {
        // 根据密钥字符串生成HMAC SHA-256密钥
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        // 配置JWT解析器，要求JWT的发行者为weblog，并使用指定密钥验证签名
        return Jwts.parser()
                .requireIssuer(ISSUER)
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
