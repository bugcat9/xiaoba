package com.xiaoba.service.impl;

import com.xiaoba.entity.Question;
import com.xiaoba.mapper.QuestionMapper;
import com.xiaoba.service.QuestionService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

@SpringBootTest
public class QuestionServiceImplTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionMapper questionMapper;

    @Test
    public void testAskQuestion() {
        boolean res= questionService.askQuestion("wjf","为什么感觉wjf有点丑","自己长得太丑怎么办？");
        Assert.assertTrue(res);
        List<Question> list=questionService.getQuestionsBySomeone("wjf");
        boolean flag=false;
        for(Question q:list){
            if(q.getQuestionTitle().equals("为什么感觉wjf有点丑")){
                flag=true;
            }
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void testDeleteQuestion() {
        List<Question> list=questionService.getQuestionsBySomeone("wjf");

        for(Question q:list){
            if(q.getQuestionTitle().equals("为什么感觉wjf有点丑")){
                boolean res= questionService.deleteQuestion(q.getQuestionId());
                Assert.assertTrue(res);
            }
        }
        list=questionService.getQuestionsBySomeone("wjf");
        boolean flag=false;
        for(Question q:list){
            if(q.getQuestionTitle().equals("为什么感觉wjf有点丑")){
                flag=true;
            }
        }
        Assert.assertFalse(flag);

    }

    @Test
    public void testGetQuestionsBySomeone() {
    }
}