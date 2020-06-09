package com.xiaoba.controller;

import com.xiaoba.entity.Essay;
import com.xiaoba.entity.Tag;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.mapper.TagMapper;
import com.xiaoba.service.EssayService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhouning
 */
@Api(tags = "文章相关的接口")
@RestController
public class EssayController {

    @Autowired
    EssayService essayService;

    @ApiOperation("得到作者文章接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "author",value = "作者名字"),
            @ApiImplicitParam(name = "pageIndex",value = "页数")

    })
    @GetMapping("/getEssaies")
    public List<Essay> getEssaies(String author,Integer pageIndex){
        return essayService.getEssaies(author,pageIndex);
    }

    @ApiOperation("得到作者文章数量")
    @ApiImplicitParam(name = "author",value = "作者名字")
    @GetMapping("/countOfEssay")
    public int countOfEssay(String author){
        return essayService.countOfAuthorEssay(author);
    }

    @GetMapping("/getEssay")
    public Essay getEssay(Integer id){
        return essayService.getEssay(id);
    }

    @ApiOperation(value = "得到所有tag接口")
    @GetMapping("/allTags")
    public List<Tag> getAllTags(Integer pageIndex){
        return essayService.getAllTags(pageIndex);
    }

    @ApiOperation(value = "得到所有tag数量")
    @GetMapping("/countOfAllTags")
    public int countOfAllTags(){
        return essayService.countOfAllTags();
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
    @PostMapping("/md")
    @ResponseBody
    public String publishEssay(String content,String title,String essayAbstract,String author){
        return essayService.publishEssay(content, title, essayAbstract, author);
    }

    @ApiOperation(value = "得到所有文章")
    @ApiImplicitParam(name = "pageIndex",value = "页数")
    @GetMapping("/allOfEssay")
    public List<Essay> allOfEssay(Integer pageIndex){
        return essayService.allOfEssay(pageIndex);
    }

    @ApiOperation(value = "得到所有文章的数量")
    @GetMapping("/countOfAllofEssay")
    public int countOfAllofEssay(){
        return essayService.countOfAllofEssay();
    }

    @ApiOperation("文章添加标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "essayId",value = "文章id"),
            @ApiImplicitParam(name = "tagName",value = "tag名字"),
    })
    @GetMapping("/addEssayTag")
    public boolean addEssayTag(int essayId, String tagName){
        return essayService.addEssayTag(essayId, tagName);
    }

    @ApiOperation("删除文章")
    @ApiImplicitParam(name = "essayId",value = "文章id")
    @GetMapping("/deleteEssay")
    public boolean deleteEssay(Integer essayId){
        return essayService.deleteEssay(essayId);
    }
}
