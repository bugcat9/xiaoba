package com.xiaoba.service.impl;

import com.xiaoba.contants.ElasticSearchContants;
import com.xiaoba.service.ElasticSearchService;
import com.xiaoba.util.HighLightBuilderUtil;
import com.xiaoba.util.PathLoadUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * @author dell
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public Map<String, Object> searchEssay(String keyWord, int currentPage) throws IOException {
        String index=ElasticSearchContants.ESSAY_INDEX;
        String[] fields={ElasticSearchContants.ESSAY_TITLE,ElasticSearchContants.ESSAY_ABSTRACT,ElasticSearchContants.ESSAY_AUTHOR};
        SearchRequest request=HighLightBuilderUtil.buildSearchRequest(index,fields,keyWord,currentPage);
        SearchResponse response= restHighLevelClient.search(request, RequestOptions.DEFAULT);
        return HighLightBuilderUtil.parseResponse(response,fields,index,currentPage);
    }

    @Override
    public Map<String, Object> searchQuestion(String keyWord, int currentPage) throws IOException {
        String index=ElasticSearchContants.QUESTION_INDEX;
        String[] fields={ElasticSearchContants.QUESTION_TITLE,ElasticSearchContants.QUESTIONER_NAME};
        SearchRequest request=HighLightBuilderUtil.buildSearchRequest(index,fields,keyWord,currentPage);
        SearchResponse response= restHighLevelClient.search(request, RequestOptions.DEFAULT);
        return HighLightBuilderUtil.parseResponse(response,fields,index,currentPage);
    }
}
