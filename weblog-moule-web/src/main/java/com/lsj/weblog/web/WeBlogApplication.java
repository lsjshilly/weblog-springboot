package com.lsj.weblog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 */

@SpringBootApplication
@ComponentScan("com.lsj.weblog.*")
public class WeBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeBlogApplication.class, args);
    }
}
