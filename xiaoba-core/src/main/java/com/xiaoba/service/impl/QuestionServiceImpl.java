package com.xiaoba.service.impl;

import com.xiaoba.constans.PathContants;
import com.xiaoba.entity.Answer;
import com.xiaoba.entity.Question;
import com.xiaoba.mapper.AnswerMapper;
import com.xiaoba.mapper.QuestionMapper;
import com.xiaoba.service.FileService;
import com.xiaoba.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 王文旭
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final static Integer PAGE_SIZE = 5;
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    FileService fileService;

    @Override
    public String askQuestion(String asker,String questionTitle, String questionContent) {
        String file= UUID.randomUUID().toString();
        Question question=new Question();
        question.setQuestionTitle(questionTitle);
        question.setQuestionerName(asker);
        question.setAnswerNum(0);
        java.util.Date utilDate=new java.util.Date();
        question.setQuestionTime(new Date(utilDate.getTime()));
        String path = fileService.writeToMd(questionContent, file);
        question.setSavePath(path);
        int result=questionMapper.insertQuestion(question);
        return PathContants.ESSAY_PATH+path;
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        Question question = questionMapper.findQuestionById(questionId);
        if (question==null){
            return true;
        }
        int pageIndex = 0;
        List<Answer> answers = answerMapper.getAnswerOfQuestion(questionId,pageIndex,50);
        //删除对应的文件
        while (answers.size()>0){
            for (Answer answer: answers) {
                fileService.deletFile(answer.getSavePath());
            }
            pageIndex++;
            answers = answerMapper.getAnswerOfQuestion(questionId,pageIndex,50);
        }

        answerMapper.deleteAnswersByQurstionId(questionId);
        fileService.deletFile(question.getSavePath());
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

    @Override
    public boolean updateQuestion(int questionId, String questionName, String content) {
        Question question = questionMapper.findQuestionById(questionId);
        question.setQuestionerName(questionName);
        String file= UUID.randomUUID().toString();
        String path = fileService.writeToMd(content, file);
        fileService.deletFile(question.getSavePath());
        question.setSavePath(path);
        int res = questionMapper.updateQuestion(question);
        return res==1;
    }


}
