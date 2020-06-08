package com.xiaoba.mapper;

import com.xiaoba.entity.Essay;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author zhouning
 */
@Mapper
public interface EssayMapper {

    /**
     * 插入文章
     * @param essay
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "essayId")
    @Insert("insert into essay(essay_title,essay_abstract,essay_author,essay_publish_time,save_path) values(#{essayTitle},#{essayAbstract},#{essayAuthor},#{essayPublishTime},#{savePath})")
    int insertEssay(Essay essay);

    @Delete("delete from essay where essay_id=#{essayId}")
    int deleteEssayById(Integer essayId);

    @Update("update essay set essay_title=#{essayTitle},essay_author=#{essayAuthor},essay_publish_time=#{essayPublishTime},save_path=#{savePath}")
    int updateEssay(Essay essay);

    @Select("select * from essay where essay_title=#{essayTitle} and essay_author=#{essayAuthor} and essay_publish_time=#{essayPublishTime}")
    Essay getEssay(String essayTitle, String essayAuthor, Date essayPublishTime);

    @Select("select * from essay where essay_id=#{essayId}")
    Essay findEssayById(Integer essayId);

    @Select("select * from essay where essay_author=#{essayAuthor} order by essay_publish_time limit ${pageIndex*count},#{count}")
    List<Essay> listEssay(String essayAuthor,int pageIndex,int count);

    @Select("select count(*) from essay where essay_author=#{essayAuthor}")
    int countOfAuthorEssay(String essayAuthor);

    /**
     * 当文章被评论时，评论数加一
     * @param essayId 文章id
     * @return 修改成功返回1
     */
    @Update("update essay set comment_num=comment_num+1 where essay_id=#{essayId}")
    int addCommentNum(Integer essayId);

    /**
     * 文章评论被删时，评论数减一
     * @param essayId 文章id
     * @return 修改成功返回1
     */
    @Update("update essay set comment_num=comment_num-1 where essay_id=#{essayId} and comment_num>0")
    int decCommentNum(Integer essayId);

}
