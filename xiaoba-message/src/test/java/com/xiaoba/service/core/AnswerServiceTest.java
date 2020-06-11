package com.xiaoba.service.core;

import com.xiaoba.entity.Answer;
import com.xiaoba.entity.AnswerFrom;
import com.xiaoba.service.AnswerService;
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
public class AnswerServiceTest {

    @Autowired
    AnswerService answerService;

    @Test
    public void answerQuestion() {
        String res= answerService.answerQuestion("王文旭",2, "回答内容");
        System.out.println("回答一个问题");
        System.out.println(res);
    }

    @Test
    public void deleteAnswer() {
        boolean res= answerService.deleteAnswer(1);
        System.out.println("删除一条回答："+res);
    }

    @Test
    public void getAnswerBySomeone() {
        List<AnswerFrom> answers= answerService.getAnswerBySomeone("user01",0);
        System.out.println("获取用户的所有回答：");
        System.out.println(answers);
    }

    @Test
    public void countofSbAnswer() {
        int count=answerService.CountofSbAnswer("user01");
        System.out.println("获取用户的回答的数量:"+count);
    }

    @Test
    public void getAnswerByQuestion() {
        List<Answer> answers= answerService.getAnswerByQuestion(1,0);

        System.out.println("获取一个问题的所有回答");
        System.out.println(answers);
    }

    @Test
    public void countOfQuestionAnswer() {
        int count= answerService.countOfQuestionAnswer(1);
        System.out.println("一个问题的回答数："+count);
    }

    @Test
    public void allAnswers() {
        List<Answer> answers=answerService.allAnswers(0);
        System.out.println("获取所有问答");
        System.out.println(answers);
    }

    @Test
    public void countOfAllAnswers() {
        int count=answerService.countOfAllAnswers();
        System.out.println("获取所有的回答的数量："+count);
    }
}