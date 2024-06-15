package com.lsj.weblog.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 */

@SpringBootApplication
@ComponentScan("com.lsj.weblog.*")
@MapperScan(value = {"com.lsj.weblog.*.mapper"})
public class WeBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeBlogApplication.class, args);
    }
}
