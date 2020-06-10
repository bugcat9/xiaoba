package com.xiaoba.controller;

import com.xiaoba.service.ElasticSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author zhouning
 */
@Api(tags = "搜索的接口")
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @ApiOperation(value = "搜索文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword",value = "关键字"),
            @ApiImplicitParam(name = "currentPage",value = "搜索的页数")
    })
    @ResponseBody
    @GetMapping("/essay")
    public Map<String,Object> searchEssay(String keyword, int currentPage) throws IOException {
        return elasticSearchService.searchEssay(keyword,currentPage);
    }

    @ApiOperation(value = "搜索问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword",value = "关键字"),
            @ApiImplicitParam(name = "currentPage",value = "搜索的页数")
    })
    @ResponseBody
    @GetMapping("/question")
    public Map<String,Object> searchQuestion(String keyword,int currentPage) throws IOException {
       return elasticSearchService.searchQuestion(keyword, currentPage);
    }


}
