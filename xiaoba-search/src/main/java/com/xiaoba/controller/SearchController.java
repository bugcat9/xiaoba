package com.xiaoba.controller;

import com.xiaoba.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @ResponseBody
    @GetMapping("/search")
    public Map<String,Object> search(String keyword, int currentPage) throws IOException {
        return elasticSearchService.search(keyword,currentPage);
    }
}
