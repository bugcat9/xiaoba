package com.xiaoba.aspect;

import com.xiaoba.entity.Essay;
import com.xiaoba.service.CommentService;
import com.xiaoba.service.EssayService;
import com.xiaoba.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CommentAspect {

    @Autowired
    EssayService essayService;

    @Autowired
    MessageService messageService;

    @Pointcut("execution(* com.xiaoba.controller.CommentController.addCommentOfEssay(..))")
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
            Integer id = (Integer) args[0];
            String commentName = (String) args[1];
            //发送消息
            Essay essay = essayService.getEssay(id);
            if (essay!=null){
                String msg = "你的文章："+essay.getEssayTitle()+"被"+commentName+"评论";
                messageService.sendMessage(essay.getEssayAuthor(), msg);
                //数据库存储
                messageService.sendMessage( commentName, essay.getEssayAuthor(), msg);
            }

        }
    }
}
