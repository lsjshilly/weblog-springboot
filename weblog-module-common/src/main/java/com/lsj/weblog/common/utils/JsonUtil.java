package com.lsj.weblog.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    // 创建ObjectMapper实例
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 静态代码块，用于初始化ObjectMapper配置
    static {
        // 禁用反序列化时遇到未知属性抛出异常的特性
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 设置序列化时忽略值为null的属性
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    // 将对象转换为JSON字符串的方法
    public static String toJsonStr(Object object) {
        try {
            // 使用ObjectMapper将对象转换为JSON字符串
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // 如果转换失败，返回对象的toString()方法的结果
            return object.toString();
        }
    }
}
