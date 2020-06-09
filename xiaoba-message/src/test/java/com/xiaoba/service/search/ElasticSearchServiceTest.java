package com.xiaoba.service.search;

import com.xiaoba.service.ElasticSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticSearchServiceTest {

    @Autowired
    ElasticSearchService elasticSearchService;

    @Test
    public void searchEssay() throws IOException {
        elasticSearchService.searchEssay("java",0);
    }

    @Test
    public void searchQuestion() throws IOException {
        elasticSearchService.searchQuestion("什么",0);
    }
}