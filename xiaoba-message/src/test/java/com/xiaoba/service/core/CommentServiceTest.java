package com.xiaoba.service.core;

import com.xiaoba.entity.UserComment;
import com.xiaoba.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    public void listCommentsByEssay() {
        List<UserComment> comments= commentService.listCommentsByEssay(1,0);
        System.out.println("按页获取文章的评论");
        System.out.println(comments);
    }

    @Test
    public void countOfEssayCommnets() {
        int count=commentService.countOfEssayCommnets(1);
        System.out.println("获取一篇文章下的评论数："+count);

    }

    @Test
    public void getCommentsOfAnswer() {
        List<UserComment> comments= commentService.getCommentsOfAnswer(1,0);
        System.out.println("获取回答的评论");
        System.out.println(comments);
    }

    @Test
    public void countOfAnswerComent() {
        int count=commentService.countOfAnswerComent(1);
        System.out.println("获取一篇回答下的评论数："+count);
    }

    @Test
    public void getCommentsOfComment() {
        List<UserComment> comments= commentService.getCommentsOfComment(1,0);
        System.out.println("获取评论的评论");
        System.out.println(comments);
    }

    @Test
    public void countOfCommentComment() {
        int count=commentService.countOfCommentComment(1);
        System.out.println("获取一篇评论下的评论数："+count);
    }

    @Test
    public void getCommentsOfSb() {
        List<UserComment> comments= commentService.getCommentsOfSb("user01",0);
        System.out.println("获取用户的评论");
        System.out.println(comments);
    }

    @Test
    public void countOfSbComments() {
        int count=commentService.countOfSbComments("user01");
        System.out.println("获取用户发过的评论数："+count);
    }

    @Test
    public void deleteCommentsOfEssay() {
        boolean res=commentService.deleteCommentsOfEssay(1);
        System.out.println("删除文章的所有评论:"+res);
    }

    @Test
    public void deleteCommentsOfAnswer() {
        boolean res=commentService.deleteCommentsOfAnswer(1);
        System.out.println("删除回答的所有评论:"+res);
    }

    @Test
    public void deleteCommentsOfComment() {
        boolean res=commentService.deleteCommentsOfComment(1);
        System.out.println("删除评论的所有评论:"+res);
    }

    @Test
    public void addCommentOfEssay() {
        boolean res= commentService.addCommentOfEssay(2,"user01","测试评论");
        System.out.println("对一篇文章发表评论:"+res);
    }

    @Test
    public void addCommentOfAnswer() {
        boolean res= commentService.addCommentOfAnswer(2,"user01","测试评论");
        System.out.println("对一篇文章发表评论:"+res);
    }

    @Test
    public void addCommentOfComment() {
        boolean res= commentService.addCommentOfComment(2,"user01","测试评论");
        System.out.println("对一篇文章发表评论:"+res);
    }

    @Test
    public void deleteCommentById() {
        boolean res= commentService.deleteCommentById(1);
        System.out.println("对一篇文章发表评论:"+res);
    }
}