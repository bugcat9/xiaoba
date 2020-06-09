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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author zhouning
 */
@Api(tags = "提问相关接口")
@RequestMapping("/question")
@RestController
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
    public String askQuestion(String asker,String questionTitle,String questionContent){
        return questionService.askQuestion(asker, questionTitle, questionContent);
    }

    @ApiOperation(value = "得到提问者提的问题相关接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "asker" ,value = "提问者"),
            @ApiImplicitParam(name = "pageIndex" ,value = "页数"),

    })
    @GetMapping("/getQuestions")
    @ResponseBody
    public List<Question> getQuestionsBySomeone(String asker,Integer pageIndex){
        return questionService.getQuestionsBySomeone(asker,pageIndex);
    }

    @ApiOperation(value = "得到提问者提的问题数量接口")
    @ApiImplicitParam(name = "asker" ,value = "提问者")
    @GetMapping("/countOfSbQuestion")
    public int countOfSbQuestion(String asker){
        return questionService.countOfSbQuestions(asker);
    }

    @ApiOperation(value = "得到所有问题")
    @ApiImplicitParam(name = "pageIndex" ,value = "页数")
    @GetMapping("/allQuestions")
    public List<Question> allQuestions(Integer pageIndex){
        return questionService.allQuestions(pageIndex);
    }


    @ApiOperation(value = "得到所有问题的数量")
    @GetMapping("/countOfAllQuestions")
    public int countOfAllQuestions(){
        return questionService.countOfQuestions();
    }

    @ApiOperation(value = "通过id得到问题")
    @ApiImplicitParam(name = "questionId" ,value = "问题id")
    @GetMapping("/getQuestion")
    public Question findQuestionById(Integer questionId){
        return questionService.findQuestionById(questionId);
    }

    @ApiOperation(value = "删除问题")
    @ApiImplicitParam(name = "questionId" ,value = "问题id")
    @GetMapping("/deleteQuestion")
    public boolean deleteQuestion(int questionId){
        return questionService.deleteQuestion(questionId);
    }
}
