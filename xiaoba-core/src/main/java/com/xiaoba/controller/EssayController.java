package com.xiaoba.controller;

import com.xiaoba.entity.Essay;
import com.xiaoba.mapper.EssayMapper;
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
    EssayMapper essayMapper;

    @GetMapping("/getEssaies")
    public List<Essay> getEssay(String author){
        return essayMapper.listEssay(author);
    }

    @GetMapping("/getEssay")
    public Essay getEssay(Integer id){
        return essayMapper.findEssayById(id);
    }
}
