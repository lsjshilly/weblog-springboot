package com.lsj.weblog.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jAdminConfiguration {

    @Bean(value = "adminApi")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                //分组名称
                .groupName("admin")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.lsj.weblog.security.controller").or(RequestHandlerSelectors.basePackage("com.lsj.weblog.controller")))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                //.title("swagger-bootstrap-ui-demo RESTful APIs")
                .description("weblog 管理后台API")
                .termsOfServiceUrl("https://github.com/lsjshilly/weblog-springboot.git")
                .contact(new Contact("xxx", "https://github.com/lsjshilly/weblog-springboot.git", ""))
                .version("1.0")
                .build();
    }
}