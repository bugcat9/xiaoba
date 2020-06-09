package com.xiaoba.service.core;

import com.xiaoba.entity.Question;
import com.xiaoba.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Test
    public void askQuestion() {
        String res =questionService.askQuestion("user01","user01的测试问题标题", "测试内容");
        System.out.println("提问结果："+res);
    }

    @Test
    public void deleteQuestion() {
        boolean res= questionService.deleteQuestion(1);
        System.out.println("删除id为1的评论"+res);
    }

    @Test
    public void getQuestionsBySomeone() {
        List<Question> questionList =questionService.getQuestionsBySomeone("user01",0);
        System.out.println("获取用户所有问题");
        System.out.println(questionList);
    }

    @Test
    public void countOfSbQuestions() {
        int count= questionService.countOfSbQuestions("user01");
        System.out.println("用户的问题数："+count);
    }

    @Test
    public void allQuestions() {
        List<Question> questionList=questionService.allQuestions(0);
        System.out.println("获取所有问题，第一页：");
        System.out.println(questionList);
    }

    @Test
    public void countOfQuestions() {
        int count =questionService.countOfQuestions();
        System.out.println("获取问题数");
    }

    @Test
    public void findQuestionById() {
        Question question=questionService.findQuestionById(1);
        System.out.println("获取某个问题:");
        System.out.println(question);
    }
}