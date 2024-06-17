package com.lsj.weblog.web;

import com.lsj.weblog.security.domain.entity.User;
import com.lsj.weblog.security.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;


@SpringBootTest(classes = WeBlogApplication.class)
@Slf4j
class WeBlogApplicationTest {


    @Resource
    private UserMapper userMapper;

    @Test
    void test() {
        log.info("this is a info log");
        log.warn("this is a warn log");
        log.error("this is a error log");
    }

    @Test
    void test2() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername("test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setNickname("测试账号");
        user.setRealname("test");
        user.setSex((short) 1);


        userMapper.insert(user);
    }


}