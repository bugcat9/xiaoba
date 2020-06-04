package com.xiaoba.controller;

import com.xiaoba.entity.UserComment;
import com.xiaoba.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/getComments")
    public List<UserComment> listCommentsByEssay(Integer essayId){
        return commentService.listCommentsByEssay(essayId);
    }

}
