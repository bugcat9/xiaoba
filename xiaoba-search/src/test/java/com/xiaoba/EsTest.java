package com.xiaoba;

import com.xiaoba.entity.Essay;
import com.xiaoba.entity.EssayIndex;
import com.xiaoba.mapper.EssayMapper;
import com.xiaoba.repository.EssayIndexRepository;
import com.xiaoba.service.Impl.ElasticSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class EsTest extends AbstractTestNGSpringContextTests {
    @Autowired
    EssayIndexRepository essayIndexRepository;

    @Autowired
    EssayMapper essayMapper;

    @Autowired
    ElasticSearchServiceImpl elasticSearchService;

    @Test
    public void testInit(){
//        //List<Essay> essayList= essayMapper.getAllEssay();
//        Assert.assertNotNull(essayList);
//        essayIndexRepository.saveAll(essayList.stream()
//                .map(elasticSearchService::buildEssayIndex)
//                .collect(Collectors.toList()));
    }

    @Test
    public void testAdd(){
        EssayIndex essayIndex= new EssayIndex();
        essayIndex.setEssayId(1);
        essayIndex.setEssayAuthor("张三");
        essayIndexRepository.save(essayIndex);
    }
}
