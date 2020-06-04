package com.xiaoba.controller;

import com.xiaoba.entity.Essay;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhouning
 */
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


}
