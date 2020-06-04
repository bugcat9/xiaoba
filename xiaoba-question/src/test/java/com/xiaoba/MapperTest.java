package com.xiaoba;

import com.xiaoba.entity.Question;
import com.xiaoba.mapper.AnswerMapper;
import com.xiaoba.mapper.QuestionMapper;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapperTest extends AbstractTestNGSpringContextTests {

    @Autowired
    QuestionMapper questionMapper;

    @Test
    public  void testInsert(){
        Question question=new Question();
        question.setQuestionerName("user01");
        question.setQuestionTime(new Date());
        question.setQuestionContent("为什么wjf长得那么丑？");
        questionMapper.insertQuestion(question);
    }

    @Test
    public  void testDelete(){
        int res=questionMapper.deleteQuestionById(6);
        System.out.print(res);
    }

    @Test
    public  void testUpdate(){
        Question question=new Question();
        question.setQuestionId(5);
        question.setQuestionerName("user02");
        question.setQuestionTime(new Date());
        question.setQuestionContent("为什么zn长得那么丑？");
        questionMapper.updateQuestion(question);
    }

    @Test
    public  void testFindByID(){

        Question question= questionMapper.findQuestionById(5);
        System.out.print(question.toString());
    }

    @Test
    public  void testGetBySb(){
        List<Question> questions= questionMapper.getQuestionOfSb("user01");
        System.out.print(questions);
    }
}
