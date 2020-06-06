package com.xiaoba.service.impl;

import com.xiaoba.entity.Answer;
import com.xiaoba.mapper.AnswerMapper;
import com.xiaoba.service.AnswerService;
import com.xiaoba.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 王文旭
 */
@Service
public class AnswerServiceImpl implements AnswerService {
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
        answer.setAnswerTime(new Date());
        int result=answerMapper.insertAnswer(answer);

        return path;
    }

    @Override
    public boolean deleteAnswer(int answerId) {
        int result=answerMapper.deleteAnswerById(answerId);
        return result==1;
    }

    @Override
    public List<Answer> getAnswerBySomeone(String anserer) {
        return answerMapper.getAnswerOfSb(anserer);
    }

    @Override
    public List<Answer> getAnswerByQuestion(int questionId) {
        return answerMapper.getAnswerOfQuestion(questionId);
    }
}
