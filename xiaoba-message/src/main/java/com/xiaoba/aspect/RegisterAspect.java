package com.xiaoba.aspect;


import com.xiaoba.entity.Question;
import com.xiaoba.service.MessageService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RegisterAspect {
    @Autowired
    MessageService messageService;

    @Pointcut("execution(* com.xiaoba.controller.RegisterController.register(..))")
    public void declareJointPointExpression(){}

    @AfterReturning(value="declareJointPointExpression()", returning="result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知 The method " + methodName + " ends with " + result);
        Object [] args = joinPoint.getArgs();
        for (Object arg:args) {
            System.out.println("参数"+arg);
        }
        boolean res = (boolean) result;
//        if (res){
//            String userName = (String) args[0];
//            messageService.createQueue(userName);
//        }
    }
}
