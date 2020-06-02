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
    void updateEssay(Essay essay);

    @Select("select * from essay where essay_title=#{essayTitle} and essay_author=#{essayAuthor} and essay_publish_time=#{essayPublishTime}")
    Essay getEssay(String essayTitle, String essayAuthor, Date essayPublishTime);

    @Select("select * from essay where essay_author=#{essayAuthor}")
    List<Essay> listEssay(String essayAuthor);

}
