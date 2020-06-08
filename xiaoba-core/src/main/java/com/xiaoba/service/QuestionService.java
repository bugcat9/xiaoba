package com.xiaoba.service;

import com.xiaoba.entity.Question;

import java.util.List;

/**
 * @author dell
 */
public interface QuestionService {

    /**
     * 提问
     * @param asker 提问者的用户名
     * @param questionContent 提问内容
     * @return 成功发出问题返回true
     */
     String  askQuestion(String asker,String questionTitle,String questionContent);

    /**
     * 根据问题ID删除问题
     * @param questionId 问题的ID
     * @return 删除成功返回true
     */
     boolean deleteQuestion(int questionId);

    /**
     * 获得某个用户提出的问题
     * @param asker 用户名
     * @param pageIndex
     * @return 问题列表
     */
     List<Question> getQuestionsBySomeone(String asker,Integer pageIndex);

     int countOfSbQuestions(String asker);

     List<Question> allQuestions(Integer pageIndex);

     int countOfQuestions();

     Question findQuestionById(Integer questionId);
}
