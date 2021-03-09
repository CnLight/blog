package com.wl.blog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAop {
    //提取切面表达式
    @Pointcut("execution(* com.wl.blog.service.*.*.*(..))")
    public void aopCut() {
    }

    @Around(value = "aopCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //before 再调用构造方法之前
        System.out.println("环绕构造方法前" + proceedingJoinPoint.getSignature().getName());
        Object[] args = proceedingJoinPoint.getArgs();

        Object proceed = proceedingJoinPoint.proceed(args);
        System.out.println("环绕调用方法后" + proceedingJoinPoint.getSignature().getName());
        return proceed;
    }
}
