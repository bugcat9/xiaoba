package com.xiaoba.mapper;

import com.xiaoba.entity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhouning
 */
@Mapper
public interface TagMapper {


    /**
     * 返回文章的标签
     * @param essayId 文章的id
     * @return
     */
    @Select("select * from t_essay_tag where essay_id=#{essayId}")
    List<Tag> getTagsByEssayId(Integer essayId);

    /**
     *添加标签
     * @param tag
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "tagId")
    @Insert("insert into tag(tag_id,tag_name) values(#{tagId},#{tagName})")
    int addTag(Tag tag);

    /**
     * 返回所有的tag
     * @param pageIndex
     * @param count
     * @return
     */
    @Select("select * from tag  limit ${pageIndex*count},#{count}")
    List<Tag> listTags(int pageIndex,int count);

    /**
     * 返回所有tag的数量
     * @return
     */
    @Select("select count(*) from tag")
    int countOfTags();

    /**
     * 添加tag
     * @param essayId
     * @param tagName
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into  t_essay_tag(essay_id,tag_name) values(#{essayId},#{tagName})")
    int addEssayTag(int essayId,String tagName);

    /**
     * 得到tag
     * @param tagName
     * @return
     */
    @Select("select * from tag where tag_name=#{tagName}")
    Tag getTagsByName(String tagName);
}
