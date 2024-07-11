package com.lsj.weblog.web;

import com.lsj.weblog.admin.mapper.ArticleTagMapper;
import com.lsj.weblog.admin.model.entity.ArticleTag;
import com.lsj.weblog.admin.service.impl.InsertBatchService;
import com.lsj.weblog.security.domain.entity.User;
import com.lsj.weblog.security.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = WeBlogApplication.class)
@Slf4j
class WeBlogApplicationTest {


    @Resource
    private InsertBatchService<ArticleTag> insertBatchService;


    @Resource
    private ArticleTagMapper articleTagMapper;

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

    @Test
    void testInsertBatch() {
        String statement = "com.lsj.weblog.admin.mapper.ArticleTagMapper.insert";

        List<ArticleTag> articleTags = new ArrayList<>();
        // 生成50万数据
        for (long i = 0; i < 10000; i++) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(i);
            articleTag.setTagId(i);
            articleTags.add(articleTag);
        }

        System.out.println("time start " + LocalDateTime.now());
        int i = insertBatchService.insertBatch(articleTags, statement);
        System.out.println("time end " + LocalDateTime.now());
    }


}