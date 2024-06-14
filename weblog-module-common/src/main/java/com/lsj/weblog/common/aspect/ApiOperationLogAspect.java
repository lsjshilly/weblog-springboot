package com.lsj.weblog.common.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ApiOperationLogAspect {


    @Pointcut("@annotation(com.lsj.weblog.common.aspect.ApiOperationLog)")
    void pointcut() {
    }


    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {

        // 记录开始时间
        long start = System.currentTimeMillis();
        // 记录类和方法
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        // 请求入参
        Object[] args = joinPoint.getArgs();


    }

}
