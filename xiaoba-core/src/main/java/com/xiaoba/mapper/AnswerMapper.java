package com.xiaoba.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface AnswerMapper{

    /**
     *添加回答
     * @param anwer
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "answerId")
    @Insert("insert into answer (question_id,answerer,answer_time,save_path) " +
            "values (#{questionId},#{answerer},#{answerTime},#{savePath})")
    int insertAnswer(Answer anwer);

    /**
     *根据Anser的id删除这个Answer
     * @param answerId
     * @return
     */
    @Delete("delete from answer where answer_id=#{answerId}")
    int deleteAnswerById(Integer answerId);

    /**
     *  更新anwer表
     * @param answer
     * @return
     */
    @Update("update answer set question_id=#{questionId}," +
            "answerer=#{answerer},answer_time=#{answerTime},save_path=#{savePath} " +
            "where answer_id=#{answerId}")
    int updateAnswer(Answer answer);

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
     * @param pageIndex
     * @param count
     * @return
     */
    @Select("select * from answer where answerer=#{answerer} order by answer_time limit ${pageIndex*count},#{count}")
    List<Answer> getAnswerOfSb(String answerer,int pageIndex,int count);

    /**
     * 返回 answerer 回答问题的数量
     * @param answerer
     * @return
     */
    @Select("select count(*) from answer where answerer=#{answerer} ")
    int countOfSbAnswer(String answerer);

    /**
     * 通过问题id获得该问题的所有回答
     * @param questionId
     * @param pageIndex
     * @param count
     * @return
     */
    @Select("select * from answer where question_id=#{questionId} order by answer_time limit ${pageIndex*count},#{count}")
    List<Answer> getAnswerOfQuestion(Integer questionId,int pageIndex,int count);

    /**
     * 返回 questionId问题回答的 数量
     * @param questionId
     * @return
     */
    @Select("select count(*) from  answer where question_id=#{questionId}")
    int countOfQuestionAnswer(Integer questionId);

    /**
     * 获得所有回答
     * @param pageIndex
     * @param count
     * @return
     */
    @Select("select * from answer order by answer_time limit ${pageIndex*count},#{count}")
    List<Answer> allAnswers(int pageIndex,int count);

    /**
     * 返回 回答的数量
     * @return
     */
    @Select("select count(*) from answer")
    int countOfAllAnswer();

    /**
     * 更新用户名时，更新answer表中，回答者的名字
     * @param oldName
     * @param lastName
     * @return
     */
    @Update("UPDATE answer SET answerer=#{lastName} WHERE answerer=#{oldName}")
    int updateAnswerName(String oldName,String lastName);
}
