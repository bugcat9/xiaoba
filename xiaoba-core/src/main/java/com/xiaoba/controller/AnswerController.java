package com.xiaoba.controller;

import com.xiaoba.entity.Answer;
import com.xiaoba.service.Impl.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/answer")
@Controller
public class AnswerController {
    @Autowired
    AnswerServiceImpl answerService;

    @GetMapping("/answer")
    public boolean answerQuestion(String answerer, int questionId, String savePath){
        return answerService.answerQuestion(answerer, questionId, savePath);
    }

    public List<Answer> getAnswerByQuestion(int questionId){
        return answerService.getAnswerByQuestion(questionId);
    }
}
