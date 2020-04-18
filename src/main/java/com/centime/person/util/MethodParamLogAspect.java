package com.centime.person.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MethodParamLogAspect {

    @Around("@annotation(LogMethodParam)")
    public Object logMethodParam(ProceedingJoinPoint joinPoint) throws Throwable{
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        log.info("Method {} in class {} is called and following are the parameters", codeSignature.getName(), codeSignature.getDeclaringTypeName());
       String[] paramNames = codeSignature.getParameterNames();
       Object[] arguments = joinPoint.getArgs();
       int i=0;
       for(String paramName : paramNames) {
           log.info("{} : {}",paramName, arguments[i++]);
       }
        return joinPoint.proceed();
    }

}
