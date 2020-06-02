package com.xiaoba.service.Impl;

import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

@SpringBootTest
public class ElasticSearchServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ElasticSearchServiceImpl elasticSearchService;


    @Test
    public void testBuildEssayIndex() {

    }

    @Test
    public void testSearch() throws IOException {
         elasticSearchService.search("好一篇文章",0);
    }
}