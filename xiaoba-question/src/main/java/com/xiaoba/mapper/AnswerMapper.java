package com.xiaoba.mapper;

import com.xiaoba.entity.Answer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王文旭
 */
@Mapper
@Repository
public interface AnswerMapper {

    /**
     *添加回答
     * @param anwer
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "answerId")
    @Insert("insert into answer (question_id,answerer,answer_time,save_path) " +
            "values (#{questionId},#{answer},#{answerTime},#{savePath})")
    int insertAnswer(Answer anwer);

    /**
     *根据Anser的id删除这个Answer
     * @param answerId
     * @return
     */
    @Delete("delete from answer where answer_id=#{answerId}")
    int deleteAnswerById(Integer answerId);

    /**
     *更新Answer
     * @param answer
     */
    @Update("update answer set question_id=#{questionId}," +
            "answerer=#{answerer},answer_time=#{answerTime},save_path=#{savePath} " +
            "where answer_id=#{answerId}")
    void updateAnswer(Answer answer);

    /**
     *通过answer_id查找answer
     * @param answerId
     * @return
     */
    @Select("select * from answer where answer_id=#{answerId}")
    Answer findAnswerById(Integer answerId);

    /**
     *通过用户名获得这个人的所有回答
     * @param answerer
     * @return
     */
    @Select("select * from answer where answerer=#{answerer}")
    List<Answer> getAnswerOfSb(String answerer);

    /**
     * 通过问题id获得该问题的所有回答
     * @param questionId
     * @return
     */
    @Select("select * from answer where question_id=#{questionId}")
    List<Answer> getAnswerOfQuestion(Integer questionId);


}
