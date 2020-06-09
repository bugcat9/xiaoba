package com.xiaoba.service;

import com.xiaoba.entity.Answer;
import com.xiaoba.entity.AnswerFrom;

import java.util.List;
import java.util.Map;

/**
 * @author 王文旭
 */
public interface AnswerService {

    /**
     * 回答问题
     * @param answerer 回答者的用户名
     * @param questionId 回答的问题的id
     * @param content
     * @return 成功发出回答则返回true
     */

     String answerQuestion(String answerer,int questionId,String content);

    /**
     * 根据回答的id号删除回答
     * @param answerId 回答的id号
     * @return 删除成功返回true
     */
     boolean deleteAnswer(int answerId);

    /**
     * 获得某个人的所有回答
     * @param anserer 回答者的用户名
     * @param pageIndex 页数
     * @return 回答列表
     */
    List<AnswerFrom> getAnswerBySomeone(String anserer, Integer pageIndex);


     int CountofSbAnswer(String anserer);

    /**
     * 获得某个问题下的所有回答
     * @param questionId 问题的ID号
     * @param pageIndex 页数
     * @return 回答列表
     */
     List<Answer> getAnswerByQuestion(int questionId,Integer pageIndex);

     int countOfQuestionAnswer(int questionId);

    /**
     * 获得所有回答
     * @param pageIndex 页数
     * @return  回答
     */
     List<Answer> allAnswers(Integer pageIndex);

     int countOfAllAnswers();

     boolean updateAnwser(Integer answerId,String content);
}
