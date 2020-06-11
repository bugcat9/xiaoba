package com.xiaoba.aspect;

import com.xiaoba.service.ElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhouning
 */
@Aspect
@Component
@Slf4j
public class QuestionAspect {
    @Autowired
    ElasticSearchService elasticSearchService;

    @Pointcut("execution(* com.xiaoba.controller.QuestionController.deleteQuestion(..))")
    public void declareJointPointExpression(){}

    @AfterReturning(value="declareJointPointExpression()", returning="result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        log.info("返回通知 The method " + methodName + " ends with " + result);
        Object [] args = joinPoint.getArgs();

        for (Object arg:args) {
            log.info("参数"+arg);
        }
        boolean res = (boolean) result;
        if (res){
            Integer questionId = (Integer) args[0];
            elasticSearchService.deleteQuestionIndex(questionId);
        }
    }
}
