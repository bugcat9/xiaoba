package com.xiaoba.aspect;

import com.xiaoba.entity.Essay;
import com.xiaoba.entity.Question;
import com.xiaoba.service.AnswerService;
import com.xiaoba.service.MessageService;
import com.xiaoba.service.QuestionService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnswerAspect {

    @Autowired
    QuestionService questionService;
    @Autowired
    MessageService messageService;

    @Pointcut("execution(* com.xiaoba.controller.AnswerController.answerQuestion(..))")
    public void declareJointPointExpression(){}

    @AfterReturning(value="declareJointPointExpression()", returning="result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知 The method " + methodName + " ends with " + result);
        Object [] args = joinPoint.getArgs();
        for (Object arg:args) {
            System.out.println("参数"+arg);
        }
        String answerer = (String) args[0];
        int questionId = (int) args[1];
        Question question = questionService.findQuestionById(questionId);
        if (question!=null){
            //发送消息
            String msg = "你的问题："+question.getQuestionTitle()+"被"+answerer+"回答";
            messageService.sendMessage(question.getQuestionerName(), msg);
            messageService.sendMessage(answerer, question.getQuestionerName(), msg);
        }

    }
}
