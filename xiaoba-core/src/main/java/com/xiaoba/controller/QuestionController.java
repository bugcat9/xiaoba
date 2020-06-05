package com.xiaoba.controller;

import com.xiaoba.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
}
