package com.xiaoba.service.impl;

import com.xiaoba.constans.PathContants;
import com.xiaoba.entity.Question;
import com.xiaoba.mapper.QuestionMapper;
import com.xiaoba.service.FileService;
import com.xiaoba.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author 王文旭
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    FileService fileService;

    @Override
    public String askQuestion(String asker,String questionTitle, String questionContent) {
        Question question=new Question();
        question.setQuestionTitle(questionTitle);
        question.setQuestionerName(asker);
        java.util.Date utilDate=new java.util.Date();
        question.setQuestionTime(new Date(utilDate.getTime()));
        String filename = questionTitle+".md";
        String path = fileService.writeToMd(questionContent, filename);
        int result=questionMapper.insertQuestion(question);
        return path;
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        int result=questionMapper.deleteQuestionById(questionId);
        return result==1;
    }

    @Override
    public List<Question> getQuestionsBySomeone(String asker) {
        List<Question> questions = questionMapper.getQuestionOfSb(asker);
        for (Question question: questions) {
            question.setSavePath(PathContants.ESSAY_PATH+question.getSavePath());
        }
        return questions;
    }
}
