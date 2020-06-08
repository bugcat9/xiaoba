package com.xiaoba.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnswerAspect {
    @Pointcut("execution(* com.xiaoba.controller.AnswerController.answerQuestion(..))")
    public void declareJointPointExpression(){}

    @AfterReturning(value="declareJointPointExpression()", returning="result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知 The method " + methodName + " ends with " + result);
    }
}
