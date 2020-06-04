package com.xiaoba.service.Impl;

import com.xiaoba.entity.UserComment;
import com.xiaoba.mapper.UserCommentMapper;
import com.xiaoba.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceimpl implements CommentService {

    @Autowired
    UserCommentMapper userCommentMapper;

    @Override
    public List<UserComment> listCommentsByEssay(Integer essayId) {
        return userCommentMapper.listComments(0,essayId);
    }
}
