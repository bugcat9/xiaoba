package com.xiaoba.controller;

import com.xiaoba.entity.Question;
import com.xiaoba.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


/**
 * @author zhouning
 */
@RequestMapping("/question")
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/askQuestion")
    public boolean askQuestion(String asker,String questionTitle,String questionContent){
        return questionService.askQuestion(asker, questionTitle, questionContent);
    }

    @GetMapping("/getQuestions")
    public List<Question> getQuestionsBySomeone(String asker){
        return questionService.getQuestionsBySomeone(asker);
    }

}
