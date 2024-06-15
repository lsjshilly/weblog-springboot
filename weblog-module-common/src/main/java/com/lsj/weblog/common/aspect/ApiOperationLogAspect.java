package com.lsj.weblog.common.aspect;


import com.lsj.weblog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

@Aspect
@Slf4j
@Component
public class ApiOperationLogAspect {


    @Pointcut("@annotation(com.lsj.weblog.common.aspect.ApiOperationLog)")
    void pointcut() {
    }


    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        try {
            // 记录开始时间
            long start = System.currentTimeMillis();

            MDC.put("traceId", UUID.randomUUID().toString());
            // 记录类和方法
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            // 请求入参
            Object[] args = joinPoint.getArgs();
            String argStr = JsonUtil.toJsonStr(args);
            String descrption = getApiOperationLogDescription(joinPoint);

            log.info("======= 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {}", descrption, argStr, className, methodName);

            Object result = joinPoint.proceed();
            long executeTime = System.currentTimeMillis() - start;
            log.info("======= 执行结束: [{}], 耗时: {}, 请求类: {}, 请求方法: {}", descrption, executeTime, className, methodName);
            return result;
        } catch (Throwable throwable) {
            throw new RuntimeException("");
        } finally {
            MDC.clear();
        }


    }

    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method == null) {
            return "";
        }

        ApiOperationLog declaredAnnotation = method.getDeclaredAnnotation(ApiOperationLog.class);
        if (declaredAnnotation == null) {
            return "";
        }


        return declaredAnnotation.description();


    }

}
