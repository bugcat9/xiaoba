package com.xiaoba.service.core;

import com.xiaoba.entity.Essay;
import com.xiaoba.entity.Tag;
import com.xiaoba.service.EssayService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class EssayServiceTest {

    @Autowired
    EssayService essayService;

    @Test
    public void getEssaies() {
        List<Essay> essays= essayService.getEssaies("user01",0);
        System.out.println(essays);
    }

    @Test
    public void countOfAuthorEssay() {
       int count= essayService.countOfAuthorEssay("user01");
       System.out.println(count);
    }

    @Test
    public void getEssay() {
        Essay essay =essayService.getEssay(1);
        System.out.println(essay);
    }

    @Test
    public void publishEssay() {
        essayService.publishEssay("内容","测试标题","测试摘要","user01",new String[]{"java"});
    }

    @Test
    public void deleteEssay() {
        boolean res= essayService.deleteEssay(1);
        System.out.println("测试删除文章，结果："+res);
    }

    @Test
    public void getAllTags() {
        List<Tag> tags =essayService.getAllTags(0);
        System.out.println("获取所有标签，第0页：");
        System.out.println(tags);
    }

    @Test
    public void countOfAllTags() {
        int count= essayService.countOfAllTags();
        System.out.println("标签数："+count);
    }

    @Test
    public void addTag() {

    }

    @Test
    public void allOfEssay() {
        List<Essay> essays =essayService.allOfEssay(0);
        System.out.println("查询所有文章，第一页：");
        System.out.println(essays);
    }

    @Test
    public void countOfAllofEssay() {
        int count=essayService.countOfAllofEssay();
        System.out.println("文章总数："+count);
    }

    @Test
    public void addEssayTag() {
    }
}