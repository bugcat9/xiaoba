package com.xiaoba.service.impl;

import com.xiaoba.constans.PathContants;
import com.xiaoba.entity.Answer;
import com.xiaoba.entity.AnswerFrom;
import com.xiaoba.entity.Question;
import com.xiaoba.mapper.AnswerMapper;
import com.xiaoba.mapper.QuestionMapper;
import com.xiaoba.service.AnswerService;
import com.xiaoba.service.CommentService;
import com.xiaoba.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.Date;
import java.util.*;

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

    @Autowired
    CommentService commentService;
    @Autowired
    QuestionMapper questionMapper;

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
        Answer answer = answerMapper.findAnswerById(answerId);
        int result=answerMapper.deleteAnswerById(answerId);
        //删除回答的所有评论
        commentService.deleteCommentsOfAnswer(answerId);
        fileService.deletFile(answer.getSavePath());
        return result==1;
    }

    @Override
    public List<AnswerFrom> getAnswerBySomeone(String anserer, Integer pageIndex) {
        List<Answer> answers =answerMapper.getAnswerOfSb(anserer,pageIndex,PAGE_SIZE);
//        List<Question> questions =new  ArrayList<>();
        List<AnswerFrom> answerFroms = new ArrayList<>();
        for (Answer answer:answers) {
            answer.setSavePath(PathContants.QUESTION_PATH+answer.getSavePath());
            Question question = questionMapper.findQuestionById(answer.getQuestionId());
            question.setSavePath(PathContants.QUESTION_PATH+question.getSavePath());
            AnswerFrom answerFrom = new AnswerFrom() ;
            answerFrom.setAnswer(answer);
            answerFrom.setQuestion(question);
            answerFroms.add(answerFrom);
        }
//        Map<String,Object> result =new HashMap<>();
//        result.put("answer",answers);
//        result.put("question",questions);
        return answerFroms;
    }

    @Override
    public int CountofSbAnswer(String anserer) {
        return answerMapper.countOfSbAnswer(anserer);
    }

    @Override
    public List<Answer> getAnswerByQuestion(int questionId,Integer pageIndex) {
        return answerMapper.getAnswerOfQuestion(questionId,pageIndex,PAGE_SIZE);
    }

    @Override
    public int countOfQuestionAnswer(int questionId) {
        return answerMapper.countOfQuestionAnswer(questionId);
    }

    @Override
    public List<Answer> allAnswers(Integer pageIndex) {
        List<Answer> answers = answerMapper.allAnswers(pageIndex,PAGE_SIZE);
        for (Answer answer:answers){
            answer.setSavePath(PathContants.ESSAY_PATH+answer.getSavePath());
        }
        return answers;
    }

    @Override
    public int countOfAllAnswers() {
        return answerMapper.countOfAllAnswer();
    }

    @Override
    public boolean updateAnwser(Integer answerId, String content) {
        return false;
    }
}
