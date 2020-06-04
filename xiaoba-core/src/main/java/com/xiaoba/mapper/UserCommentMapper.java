package com.xiaoba.mapper;

import com.xiaoba.entity.UserComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhouning
 */
@Mapper
public interface UserCommentMapper {

    @Options
    @Insert("insert into user_comment(comment_id,parent_type,parent_id,commentator_name,comment_time,comment_content) values(#{commentId},#{parentType},#{parentId},#{commentatorName},#{commentTime},#{commentContent}) ")
    int insertComment(UserComment userComment);

    @Select("Select * from user_comment where comment_id=#{commentId}")
    UserComment selectCommentById(Integer commentId);

    @Select("Select * from user_comment where parent_type=#{parentType} and parent_id=#{parentId}")
    List<UserComment> listComments(Integer parentType,Integer parentId);

    /**
     * 删除某个父对象的所有评论
     * @param parentType 父对象类型
     * @param parentId  父对象ID
     * @return 删除成功，结果不为0（注意）
     */
    @Delete("delete from user_comment where parent_type=#{parentType} and parent_id=#{parentId}")
    int deleteAllCommentOfParent(Integer parentType,Integer parentId);

    /**
     * 根据评论ID删除评论
     * @param commentId 被删除评论的ID
     * @return 返回匹配数，comment_id唯一，故删除成功返回1
     */
    @Delete("delete from user_comment where comment_id=#{commentId}")
    int deleteCommentById(Integer commentId);

    /**
     * 获得某个用户的所有评论
     * @param commentatorName 评论者
     * @return 评论列表
     */
    @Select("Select * from user_comment where commentator_name=#{commentatorName}")
    List<UserComment> getCommentsOfSb(String commentatorName);
}
