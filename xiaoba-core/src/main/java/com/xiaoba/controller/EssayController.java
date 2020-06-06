package com.xiaoba.controller;

import com.xiaoba.entity.Essay;
import com.xiaoba.entity.Tag;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.mapper.TagMapper;
import com.xiaoba.service.EssayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    TagMapper tagMapper;

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
        return tagMapper.listTags();
    }
}
