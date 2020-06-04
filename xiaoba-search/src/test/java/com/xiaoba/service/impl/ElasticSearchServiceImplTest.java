package com.xiaoba.service.impl;

import com.xiaoba.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

@SpringBootTest
public class ElasticSearchServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ElasticSearchService elasticSearchService;

    @Test
    public void testSearch() throws IOException {
        elasticSearchService.search("文章",0);
    }
}