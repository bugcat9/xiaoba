package com.xiaoba.service;

import com.xiaoba.entity.Answer;

import java.util.List;

/**
 * @author dell
 */
public interface AnswerService {

    /**
     * 回答问题
     * @param answerer 回答者的用户名
     * @param questionId 回答的问题的id
     * @param savePath 回答的md文件的保存路径
     * @return 成功发出回答则返回true
     */
    public boolean answerQuestion(String answerer,int questionId,String savePath);

    /**
     * 根据回答的id号删除回答
     * @param answerId 回答的id号
     * @return 删除成功返回true
     */
    public boolean deleteAnswer(int answerId);

    /**
     * 获得某个人的所有回答
     * @param anserer 回答者的用户名
     * @return 回答列表
     */
    public List<Answer> getAnswerBySomeone(String anserer);

    /**
     * 获得某个问题下的所有回答
     * @param questionId 问题的ID号
     * @return 回答列表
     */
    public List<Answer> getAnswerByQuestion(int questionId);
}
