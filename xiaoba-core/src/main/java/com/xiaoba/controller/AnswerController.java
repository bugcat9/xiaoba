package com.xiaoba.controller;

import com.xiaoba.entity.Answer;
import com.xiaoba.entity.AnswerFrom;
import com.xiaoba.service.AnswerService;
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
import java.util.Map;

/**
 * @author zhouning
 */
@Api(tags = "回答问题的接口")
@RequestMapping("/answer")
@RestController
public class AnswerController {
    @Autowired
    AnswerService answerService;


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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionId",value = "问题id"),
            @ApiImplicitParam(name = "pageIndex",value = "页数")
    })
    @GetMapping("/getAnswers")
    public List<Answer> getAnswerByQuestion(int questionId,int pageIndex){
        return answerService.getAnswerByQuestion(questionId,pageIndex);
    }

    @ApiOperation("得到问题回答的数量")
    @ApiImplicitParam(name = "questionId",value = "问题id")
    @GetMapping("/countOfAnswer")
    public int countOfQuestionAnswer(int questionId){
        return answerService.countOfQuestionAnswer(questionId);
    }

    @ApiOperation(value = "得到所有回答")
    @ApiImplicitParam(name = "pageIndex",value = "页数")
    @GetMapping("/allAnswers")
    List<Answer> allAnswers(int pageIndex){
        return answerService.allAnswers(pageIndex);
    }

    @ApiOperation("得到问题回答的数量")
    @GetMapping("/countOfAllAnswer")
    public int countOfAllAnswers(){
        return answerService.countOfAllAnswers();
    }

    @ApiOperation("删除回答")
    @ApiImplicitParam(name = "answerId",value = "问题id")
    @GetMapping("/deleteAnswer")
    public boolean deleteAnswer(int answerId){
        return answerService.deleteAnswer(answerId);
    }

    @ApiOperation("得到个人的回答")
    @GetMapping("/getAnswerBySomeone")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "answerName",value = "回答者"),
            @ApiImplicitParam(name = "pageIndex",value = "页数")
    })
    public List<AnswerFrom> getAnswerBySomeone(String answerName, Integer pageIndex) {
        return answerService.getAnswerBySomeone(answerName,pageIndex);
    }

    @ApiOperation("得到个人的回答数量")
    @ApiImplicitParam(name = "answerName",value = "回答者")
    @GetMapping("/CountofSbAnswer")
    public int countofSbAnswer(String answerName){
        return answerService.countofSbAnswer(answerName);
    }

    @ApiOperation("修改个人回答")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "answerId",value = "回答id"),
            @ApiImplicitParam(name = "content",value = "更新的内容")
    })
    @GetMapping("/updateAnwser")
    public boolean updateAnwser(Integer answerId, String content){
        return answerService.updateAnwser(answerId, content);
    }
}
