package com.lsj.weblog.web;

import com.lsj.weblog.web.domain.dto.UserDto;
import com.lsj.weblog.web.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
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
    void msyql() {
        UserDto userDto = new UserDto();
        userDto.setUsername("xiaoming");
        userDto.setPassword("123456");
        userDto.setIsDelete(0);

        userMapper.insert(userDto);
    }

}