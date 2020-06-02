package com.xiaoba.controller;

import com.xiaoba.service.ElasticSearchService;
import com.xiaoba.service.Impl.ElasticSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/search/{keyword}/{currentPage}")
    public Map<String,Object> search(@PathVariable("keyword")String keyword,
                                     @PathVariable("currentPage")int currentPage) throws IOException {
        return elasticSearchService.search(keyword,currentPage);
    }
}
