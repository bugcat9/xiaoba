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

    @Select("select * from tag  limit ${pageIndex*count},#{count}")
    List<Tag> listTags(int pageIndex,int count);

    @Select("select count(*) from tag")
    int countOfTags();
}
