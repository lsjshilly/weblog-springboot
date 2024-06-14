package com.lsj.weblog.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class WeBlogApplicationTest {


    @Test
    void test() {
        log.info("this is a info log");
        log.warn("this is a warn log");
        log.error("this is a error log");
    }

}