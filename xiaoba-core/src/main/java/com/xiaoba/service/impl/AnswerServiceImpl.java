package com.xiaoba.service.impl;

import com.xiaoba.constans.PathContants;
import com.xiaoba.entity.Answer;
import com.xiaoba.mapper.AnswerMapper;
import com.xiaoba.service.AnswerService;
import com.xiaoba.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 王文旭
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    private static Integer PAGE_SIZE = 5;
    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    FileService fileService;


    @Override
    public String answerQuestion(String answerer, int questionId,String content) {
        String file= UUID.randomUUID().toString();

        String path = fileService.writeToMd(content,file);
        Answer answer=new Answer();
        answer.setAnswerer(answerer);
        answer.setQuestionId(questionId);
        answer.setSavePath(file+".md");
        java.util.Date date =new java.util.Date();
        answer.setAnswerTime(new Date(date.getTime()));
        answer.setCommentNum(0);
        int result=answerMapper.insertAnswer(answer);

        return PathContants.ESSAY_PATH+path;
    }

    @Override
    public boolean deleteAnswer(int answerId) {
        int result=answerMapper.deleteAnswerById(answerId);
        return result==1;
    }

    @Override
    public List<Answer> getAnswerBySomeone(String anserer,Integer pageIndex) {
        return answerMapper.getAnswerOfSb(anserer,pageIndex,PAGE_SIZE);
    }

    @Override
    public List<Answer> getAnswerByQuestion(int questionId,Integer pageIndex) {
        return answerMapper.getAnswerOfQuestion(questionId,pageIndex,PAGE_SIZE);
    }

    @Override
    public List<Answer> allAnswers(Integer pageIndex) {
        List<Answer> answers = answerMapper.allAnswers(pageIndex,PAGE_SIZE);
        for (Answer answer:answers){
            answer.setSavePath(PathContants.ESSAY_PATH+answer.getSavePath());
        }

        return answers;
    }
}
