package com.xiaoba.service.Impl;

import com.xiaoba.entity.Answer;
import com.xiaoba.mapper.AnswerMapper;
import com.xiaoba.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 王文旭
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerMapper answerMapper;

    @Override
    public boolean answerQuestion(String answerer, int questionId, String savePath) {
        Answer answer=new Answer();
        answer.setAnswerer(answerer);
        answer.setQuestionId(questionId);
        answer.setSavePath(savePath);
        answer.setAnswerTime(new Date());
        int result=answerMapper.insertAnswer(answer);
        return result==1;
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
