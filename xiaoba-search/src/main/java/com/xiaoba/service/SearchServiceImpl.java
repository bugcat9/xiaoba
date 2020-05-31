package com.xiaoba.service;

import com.xiaoba.entity.Essay;
import com.xiaoba.entity.EssayIndex;
import com.xiaoba.repository.EssayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {


    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    EssayRepository essayRepository;

    @Override
    public EssayIndex buildEssay(Essay essay) {
        EssayIndex essayIndex=new EssayIndex();
        essayIndex.setEssayId(essay.getEssayId());
        essayIndex.setEssayTitle(essay.getEssayTitle());
        essayIndex.setEssayAbstract(essay.getEssayAbstract());
        essayIndex.setEssayAuthor(essay.getEssayAuthor());
        essayIndex.setEssayPublishTime(essay.getEssayPublishTime());
        return essayIndex;
    }

    @Override
    public Map<String, Object> search(String keywords, Integer currentPage, Integer pageSize) {
        return null;


    }
}
