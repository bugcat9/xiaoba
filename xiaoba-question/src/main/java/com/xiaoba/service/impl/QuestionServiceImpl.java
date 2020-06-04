package com.xiaoba.service.impl;

import com.xiaoba.entity.Question;
import com.xiaoba.mapper.QuestionMapper;
import com.xiaoba.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 王文旭
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public boolean askQuestion(String asker,String questionTitle, String questionContent) {
        Question question=new Question();
        question.setQuestionTitle(questionTitle);
        question.setQuestionerName(asker);
        question.setQuestionContent(questionContent);
        question.setQuestionTime(new Date());
        int result=questionMapper.insertQuestion(question);
        return result==1;
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        int result=questionMapper.deleteQuestionById(questionId);
        return result==1;
    }

    @Override
    public List<Question> getQuestionsBySomeone(String asker) {
        return questionMapper.getQuestionOfSb(asker);
    }
}
