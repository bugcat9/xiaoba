package com.xiaoba.service;

import com.xiaoba.entity.UserComment;

import java.util.List;

public interface CommentService {

   List<UserComment> listCommentsByEssay(Integer essayId);
}
