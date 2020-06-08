package com.xiaoba.controller;

import com.xiaoba.entity.UserComment;
import com.xiaoba.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "评论接口")
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("得到一篇文章的评论")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "essayId",value = "文章id"),
        @ApiImplicitParam(name = "pageIndex",value = "页数")
    })
    @GetMapping("/getComments")
    public List<UserComment> listCommentsByEssay(Integer essayId,Integer pageIndex){
        return commentService.listCommentsByEssay(essayId,pageIndex);
    }

    @ApiOperation("添加评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "essayId",value = "文章id"),
            @ApiImplicitParam(name = "commentatorName",value = "文章名字"),
            @ApiImplicitParam(name = "commentContent",value = "文章内容")
    })
    @GetMapping("/addComment")
    public boolean addComment(Integer essayId,String commentatorName,String commentContent){
        return commentService.addCommentOfEssay(essayId,commentatorName,commentContent);
    }

}
