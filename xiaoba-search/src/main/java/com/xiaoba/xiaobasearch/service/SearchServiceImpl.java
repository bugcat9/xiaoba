package com.xiaoba.xiaobasearch.service;

import com.xiaoba.entity.Essay;
import com.xiaoba.xiaobasearch.contants.SearchContants;
import com.xiaoba.xiaobasearch.entity.EssayIndex;
import com.xiaoba.xiaobasearch.repository.EssayRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
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
