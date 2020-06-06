package com.xiaoba.controller;

import com.xiaoba.entity.Question;
import com.xiaoba.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author zhouning
 */
@Api(tags = "提问相关接口")
@RequestMapping("/question")
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @ApiOperation(value = "发布问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "asker", value = "提问者"),
            @ApiImplicitParam(name = "questionTitle", value = "问题的名字"),
            @ApiImplicitParam(name = "questionContent", value = "问题的内容")
    }
    )
    @GetMapping("/askQuestion")
    @ResponseBody
    public boolean askQuestion(String asker,String questionTitle,String questionContent){
        return questionService.askQuestion(asker, questionTitle, questionContent);
    }

    @ApiOperation(value = "得到提问者提的问题相关接口")
    @ApiImplicitParam(name = "asker" ,value = "提问者")
    @GetMapping("/getQuestions")
    @ResponseBody
    public List<Question> getQuestionsBySomeone(String asker){
        return questionService.getQuestionsBySomeone(asker);
    }

}
