package com.xiaoba.service;

import com.xiaoba.entity.UserComment;

import java.util.List;

/**
 * @author zhouning
 */
public interface CommentService {

    /**
     * 得到一篇文章的评论
     * @param essayId
     * @param pageIndex
     * @return
     */
   List<UserComment> listCommentsByEssay(Integer essayId,Integer pageIndex);

    /**
     * 得到一篇文章的评论数量
     * @param essayId
     * @return
     */
   int countOfEssayCommnets(Integer essayId);

   /**
    * 获得一篇回答下的所有评论
    * @param answerId
    * @param pageIndex
    * @return
    */
    List<UserComment> getCommentsOfAnswer(Integer answerId,Integer pageIndex);

    /**
     * 返回一个回答的评论数量
     * @param answerId
     * @return
     */
    int countOfAnswerComment(Integer answerId);

   /**
    * 获得一个评论下的所有评论
    * @param commentId
    * @param pageIndex
    * @return
    */
    List<UserComment> getCommentsOfComment(Integer commentId,Integer pageIndex);

    /**
     * 返回评论的评论数量
     * @param commentId
     * @return
     */
    int countOfCommentComment(Integer commentId);

   /**
    * 获得某个人的所有评论
    * @param commentatorName 评论者
    * @param pageIndex
    * @return 评论列表
    */
    List<UserComment> getCommentsOfSb(String commentatorName,Integer pageIndex);

    /**
     * 返回一个人的评论数量
     * @param commentatorName
     * @return
     */
    int countOfSbComments(String commentatorName);

   /**
    * 当一篇文章被删除，删除这篇文章所有评论
    * @param essayId 文章ID
    * @return 删除成功返回true
    */
    boolean deleteCommentsOfEssay(Integer essayId);

   /**
    * 当一篇回答被删除，删除这篇回答下的所有评论
    * @param answerId 回答ID
    * @return 删除成功返回true
    */
    boolean deleteCommentsOfAnswer(Integer answerId);

   /**
    * 当一个评论被删除，删除这条评论下的所有评论
    * @param commmentId 评论ID
    * @return 删除成功返回true
    */
    boolean deleteCommentsOfComment(Integer commmentId);

   /**
    * 在一篇文章下发表评论
    * @param essayId 文章ID
    * @param commentatorName  评论者
    * @param commentContent 评论内容
    * @return 发表成功返回true
    */
    boolean addCommentOfEssay(Integer essayId,String commentatorName,String commentContent);

   /**
    * 在一篇回答下发表评论
    * @param answerId 评论ID
    * @param commentatorName 评论者
    * @param commentContent 评论内容
    * @return 发表成功返回true
    */
    boolean addCommentOfAnswer(Integer answerId,String commentatorName,String commentContent);

   /**
    *在一条评论下追加评论
    * @param commentId 被评论的评论ID（注意）
    * @param commentatorName 评论者
    * @param commentContent 评论内容
    * @return 发表成功返回true
    */
    boolean addCommentOfComment(Integer commentId,String commentatorName,String commentContent);

   /**
    * 根据评论ID删除这条评论
    * @param commentId 要被删除的评论的ID
    * @return 返回匹配数量，删除成功返回1
    */
    boolean deleteCommentById(Integer commentId);

    /**
     * 更新评论
     * @param commentId
     * @param commentContent
     * @return
     */
    boolean updateComment(Integer commentId,String commentContent);
}
