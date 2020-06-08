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

    private final static Integer PAGE_SIZE = 5;
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    FileService fileService;

    @Override
    public String askQuestion(String asker,String questionTitle, String questionContent) {
        Question question=new Question();
        question.setQuestionTitle(questionTitle);
        question.setQuestionerName(asker);
        question.setAnswerNum(0);
        java.util.Date utilDate=new java.util.Date();
        question.setQuestionTime(new Date(utilDate.getTime()));
        String path = fileService.writeToMd(questionContent, questionTitle);
        question.setSavePath(path);
        int result=questionMapper.insertQuestion(question);
        return PathContants.ESSAY_PATH+path;
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        int result=questionMapper.deleteQuestionById(questionId);
        return result==1;
    }

    @Override
    public List<Question> getQuestionsBySomeone(String asker,Integer pageIndex) {
        List<Question> questions = questionMapper.getQuestionOfSb(asker,pageIndex,PAGE_SIZE);
        for (Question question: questions) {
            question.setSavePath(PathContants.ESSAY_PATH+question.getSavePath());
        }
        return questions;
    }

    @Override
    public int countOfSbQuestions(String asker) {
        return questionMapper.countOfSbQuetion(asker);
    }

    @Override
    public List<Question> allQuestions(Integer pageIndex) {
        List<Question> questions =  questionMapper.allQuestions(pageIndex,PAGE_SIZE);
        for (Question question:questions) {
            question.setSavePath(PathContants.QUESTION_PATH+question.getSavePath());
        }
        return questions;
    }

    @Override
    public int countOfQuestions() {
        return questionMapper.countOfAllQuetions();
    }

    @Override
    public Question findQuestionById(Integer questionId) {
        return questionMapper.findQuestionById(questionId);
    }
}
