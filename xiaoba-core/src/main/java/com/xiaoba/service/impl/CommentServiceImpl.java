package com.xiaoba.service.impl;

import com.xiaoba.constans.SysConstants;
import com.xiaoba.entity.UserComment;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.mapper.UserCommentMapper;
import com.xiaoba.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private static Integer PAGE_SIZE = 5;

    @Autowired
    UserCommentMapper userCommentMapper;

    @Autowired
    EssayMapper essayMapper;

    @Override
    public List<UserComment> listCommentsByEssay(Integer essayId,Integer pageIndex) {
        return userCommentMapper.listComments(0,essayId,pageIndex,PAGE_SIZE);
    }


    @Override
    public List<UserComment> getCommentsOfAnswer(Integer answerId,Integer pageIndex) {
        return userCommentMapper.listComments(SysConstants.ANSWER,answerId,pageIndex,PAGE_SIZE);
    }

    @Override
    public List<UserComment> getCommentsOfComment(Integer commentId,Integer pageIndex) {
        return userCommentMapper.listComments(SysConstants.COMMENT,commentId,pageIndex,PAGE_SIZE);
    }

    @Override
    public List<UserComment> getCommentsOfSb(String commentatorName,Integer pageIndex) {
        return userCommentMapper.getCommentsOfSb(commentatorName,pageIndex,PAGE_SIZE);
    }

    @Override
    public boolean deleteCommentsOfEssay(Integer essayId) {
        int result=userCommentMapper.deleteAllCommentOfParent(SysConstants.ESSAY,essayId);
        return result!=0;
    }

    @Override
    public boolean deleteCommentsOfAnswer(Integer answerId) {
        int result=userCommentMapper.deleteAllCommentOfParent(SysConstants.ANSWER,answerId);
        return result!=0;
    }

    @Override
    public boolean deleteCommentsOfComment(Integer commmentId) {
        int result=userCommentMapper.deleteAllCommentOfParent(SysConstants.COMMENT,commmentId);
        return result!=0;
    }

    @Override
    public boolean addCommentOfEssay(Integer essayId, String commentatorName, String commentContent) {
        UserComment userComment=new UserComment();
        userComment.setParentType(SysConstants.ESSAY);
        userComment.setParentId(essayId);
        userComment.setCommentatorName(commentatorName);
        userComment.setCommentContent(commentContent);
        userComment.setCommentTime(new Date());
        int result= userCommentMapper.insertComment(userComment);
        if (result==1){
            result = essayMapper.addCommentNum(essayId);
        }else {
            return false;
        }
        //返回匹配行数
        return result==1;
    }

    @Override
    public boolean addCommentOfAnswer(Integer answerId, String commentatorName, String commentContent) {
        UserComment userComment=new UserComment();
        userComment.setParentType(SysConstants.ANSWER);
        userComment.setParentId(answerId);
        userComment.setCommentatorName(commentatorName);
        userComment.setCommentContent(commentContent);
        userComment.setCommentTime(new Date());
        int result= userCommentMapper.insertComment(userComment);
        //返回匹配行数
        return result==1;
    }

    @Override
    public boolean addCommentOfComment(Integer commentId, String commentatorName, String commentContent) {
        UserComment userComment=new UserComment();
        userComment.setParentType(SysConstants.COMMENT);
        userComment.setParentId(commentId);
        userComment.setCommentatorName(commentatorName);
        userComment.setCommentContent(commentContent);
        userComment.setCommentTime(new Date());
        int result= userCommentMapper.insertComment(userComment);
        //返回匹配行数
        return result==1;
    }

    @Override
    public boolean deleteCommentById(Integer commentId) {
        int result=userCommentMapper.deleteCommentById(commentId);
        return result==1;
    }
}