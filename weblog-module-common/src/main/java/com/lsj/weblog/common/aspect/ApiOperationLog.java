package com.lsj.weblog.common.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiOperationLog {

    /**
     * 接口描述信息
     *
     * @return 接口描述
     */
    String description() default "";
}
