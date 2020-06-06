package com.xiaoba.controller;

import com.xiaoba.entity.Answer;
import com.xiaoba.service.impl.AnswerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "回答问题的接口")
@RequestMapping("/answer")
@RestController
public class AnswerController {
    @Autowired
    AnswerServiceImpl answerService;

    @ApiOperation(value = "回答问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "answerer", value = "回答者"),
            @ApiImplicitParam(name = "questionId", value = "问题id"),
            @ApiImplicitParam(name = "content",value = "回答的内容")
    }
    )
    @GetMapping("/answerQuestion")
    public String answerQuestion(String answerer, int questionId,String content){
        return answerService.answerQuestion(answerer, questionId,content);
    }

    @ApiOperation(value = "得到问题的所有回答")
    @ApiImplicitParam(name = "questionId",value = "问题id")
    @GetMapping("/getAnswers")
    public List<Answer> getAnswerByQuestion(int questionId){
        return answerService.getAnswerByQuestion(questionId);
    }
}
