package com.xiaoba;


import com.xiaoba.entity.Answer;
import com.xiaoba.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class AnswerMappperTest extends AbstractTestNGSpringContextTests {
    @Autowired
    AnswerMapper answerMapper;

    @Test
    public  void testInsert(){
        Answer answer=new Answer();
        answer.setQuestionId(1);
        answer.setAnswerer("user01");
        answer.setAnswerTime(new Date());
        answer.setSavePath(UUID.randomUUID().toString());
        answerMapper.insertAnswer(answer);
    }

    @Test
    public  void testDelete(){
        int res=answerMapper.deleteAnswerById(7);
        System.out.print(res);
    }

    @Test
    public  void testUpdate(){
        Answer answer=new Answer();
        answer.setAnswerId(6);
        answer.setQuestionId(1);
        answer.setAnswerer("user01");
        answer.setAnswerTime(new Date());
        answer.setSavePath(UUID.randomUUID().toString());
        answerMapper.updateAnswer(answer);
    }

    @Test
    public  void testFindByID(){

        Answer answer=answerMapper.findAnswerById(6);
        System.out.print(answer.toString());
    }

    @Test
    public  void testGetBySb(){
        List<Answer> answers= answerMapper.getAnswerOfSb("user01");
        System.out.print(answers);
    }

    @Test
    public  void testGetByQ(){
        List<Answer> answers= answerMapper.getAnswerOfQuestion(1);
        System.out.print(answers);
    }
}
