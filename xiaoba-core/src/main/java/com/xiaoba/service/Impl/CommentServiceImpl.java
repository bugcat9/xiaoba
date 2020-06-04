package com.xiaoba.service.Impl;

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

    @Autowired
    UserCommentMapper userCommentMapper;

    @Autowired
    EssayMapper essayMapper;

    @Override
    public List<UserComment> listCommentsByEssay(Integer essayId) {
        return userCommentMapper.listComments(0,essayId);
    }


    @Override
    public List<UserComment> getCommentsOfAnswer(Integer answerId) {
        return userCommentMapper.listComments(SysConstants.ANSWER,answerId);
    }

    @Override
    public List<UserComment> getCommentsOfComment(Integer commentId) {
        return userCommentMapper.listComments(SysConstants.COMMENT,commentId);
    }

    @Override
    public List<UserComment> getCommentsOfSb(String commentatorName) {
        return userCommentMapper.getCommentsOfSb(commentatorName);
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
