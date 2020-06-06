package com.xiaoba.controller;

import com.xiaoba.entity.Essay;
import com.xiaoba.entity.Tag;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.mapper.TagMapper;
import com.xiaoba.service.EssayService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhouning
 */
@Api(tags = "文章相关的接口")
@RestController
public class EssayController {

    @Autowired
    EssayService essayService;

    @GetMapping("/getEssaies")
    public List<Essay> getEssaies(String author){
        return essayService.getEssaies(author);
    }

    @GetMapping("/getEssay")
    public Essay getEssay(Integer id){
        return essayService.getEssay(id);
    }

    @ApiOperation(value = "得到所有tag接口")
    @GetMapping("/allTags")
    public List<Tag> getAllTags(){
        return essayService.getAllTags();
    }

    @ApiOperation(value = "添加标签的接口")
    @ApiImplicitParam(name = "tagName",value = "标签名字")
    @GetMapping("/addTag")
    public boolean addTag(String tagName){
        return essayService.addTag(tagName);
    }

    @ApiOperation(value = "发布文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content",value = "文章内容"),
            @ApiImplicitParam(name = "title",value = "文章标题"),
            @ApiImplicitParam(name = "essayAbstract",value = "文章摘要"),
            @ApiImplicitParam(name = "author",value = "文章作者"),
    })
    @RequestMapping("/md")
    @ResponseBody
    public String publishEssay(String content,String title,String essayAbstract,String author){
        return essayService.publishEssay(content, title, essayAbstract, author);
    }
}
