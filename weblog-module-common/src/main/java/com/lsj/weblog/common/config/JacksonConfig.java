package com.lsj.weblog.common.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {


    @Bean
    public ObjectMapper objectMapper() {
        /**
         * 创建一个ObjectMapper实例，用于序列化和反序列化JSON。
         * ObjectMapper是一个强大的JSON处理工具，它可以将Java对象转换为JSON字符串，
         * 也可以将JSON字符串转换为Java对象。
         * 在这个配置中，我们对ObjectMapper进行了多个设置，以满足特定的JSON处理需求。
         *
         * @return 配置好的ObjectMapper实例。
         */
        ObjectMapper mapper = new ObjectMapper();

        // 禁止在反序列化时因为未知属性而失败，允许忽略未知属性。
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 注册Java时间模块，以支持Java 8引入的日期和时间API的序列化和反序列化。
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        // 自定义LocalDateTime的序列化和反序列化格式。
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 自定义LocalDate的序列化和反序列化格式。
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // 自定义LocalTime的序列化和反序列化格式。
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        // 将Java时间模块注册到ObjectMapper中。
        mapper.registerModule(javaTimeModule);

        // 设置时区为上海时区。
        mapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        // 只包含非空属性进行序列化，忽略空值。
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper;


    }

}
