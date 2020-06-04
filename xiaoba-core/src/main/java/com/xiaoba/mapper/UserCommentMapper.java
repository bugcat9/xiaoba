package com.xiaoba.mapper;

import com.xiaoba.entity.UserComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhouning
 */
@Mapper
public interface UserCommentMapper {

    @Options
    @Insert("insert into user_comment(comment_id,parent_type,parent_id,commentator_name,comment_time,comment_content) values(#{commentId},#{parentType},#{parentId},#{commentatorName},#{commentTime},#{comment_content}) ")
    int insertComment(UserComment userComment);

    @Select("Select * from user_comment where comment_id=#{commentId}")
    UserComment selectCommentById(Integer commentId);

    @Select("Select * from user_comment where parent_type=#{parentType} and parent_id=#{parentId}")
    List<UserComment> listComments(Integer parentType,Integer parentId);
}
