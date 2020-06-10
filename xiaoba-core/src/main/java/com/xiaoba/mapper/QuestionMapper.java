package com.xiaoba.mapper;

import com.xiaoba.entity.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王文旭
 */
@Mapper
@Repository
public interface QuestionMapper {

    /**
     * 添加回答
     * @param question
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "questionId")
    @Insert("insert into question (question_title,questioner_name,question_time,save_path,answer_num) values (#{questionTitle},#{questionerName},#{questionTime},#{savePath},#{answerNum})")
    int insertQuestion(Question question);

    /**
     * 根据question 的question_id删除问题question
     * @param questionId
     * @return
     */
    @Delete("delete from question where question_id=#{questionId}")
    int deleteQuestionById(Integer questionId);

    /**
     * 更新question
     * @param question
     * @return
     */
    @Update("update question set question_title=#{questionTitle}, questioner_name=#{questionerName}," +
            "question_time=#{questionTime},save_path=#{savePath} " +
            "where question_id=#{questionId}")
    int updateQuestion(Question question);

    /**
     * 根据question_id查找某个question
     * @param questionId
     * @return
     */
    @Select("select * from question where question_id=#{questionId}")
    Question findQuestionById(Integer questionId);

    /**
     * 根据用户名查找某个用户提出的所有问题
     * @param questioner
     * @param pageIndex
     * @param count
     * @return
     */
    @Select("select * from question where questioner_name=#{questioner} order by question_time limit ${pageIndex*count},#{count}")
    List<Question> getQuestionOfSb(String questioner,int pageIndex,int count);

    /**
     * 提问者提问的数量
     * @param questioner
     * @return
     */
    @Select("select count(*) from question where questioner_name=#{questioner}")
    int countOfSbQuetion(String questioner);

    /**
     * 所有问题
     * @param pageIndex
     * @param count
     * @return
     */
    @Select("select * from question order by question_time limit ${pageIndex*count},#{count}")
    List<Question> allQuestions(int pageIndex,int count);

    /**
     * 所有问题数量
     * @return
     */
    @Select("select count(*) from question ")
    int countOfAllQuetions();

    /**
     * 更新提问者的姓名
     * @param oldName
     * @param lastName
     * @return
     */
    @Update("UPDATE question SET questioner_name=#{lastName} WHERE questioner_name=#{oldName}")
    int updateQuestionName(String oldName,String lastName);
}
