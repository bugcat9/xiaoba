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

/**
 * @author zhouning
 */
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

    @ApiOperation("得到一篇文章的评论数量")
    @ApiImplicitParam(name = "essayId",value = "文章id")
    @GetMapping("/countOfEssayComments")
    public int countOfEssayComments(Integer essayId){
        return commentService.countOfEssayCommnets(essayId);
    }

    @ApiOperation("添加文章评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "essayId",value = "文章id"),
            @ApiImplicitParam(name = "commentatorName",value = "文章名字"),
            @ApiImplicitParam(name = "commentContent",value = "文章内容")
    })
    @GetMapping("/addComment")
    public boolean addCommentOfEssay(Integer essayId,String commentatorName,String commentContent){
        return commentService.addCommentOfEssay(essayId,commentatorName,commentContent);
    }

    @ApiOperation("删除文章评论")
    @ApiImplicitParam(name = "commentId",value = "评论id")
    @GetMapping("/deleteCommentById")
    public boolean deleteCommentById(Integer commentId) {
        return commentService.deleteCommentById(commentId);
    }

    @ApiOperation("更改文章评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId",value = "评论id"),
            @ApiImplicitParam(name = "commentContent",value = "评论内容")
    })
    @GetMapping("/updateComment")
    public boolean updateComment(Integer commentId, String commentContent){
        return commentService.updateComment(commentId, commentContent);
    }
}
