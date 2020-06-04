package com.xiaoba.service.impl;

import com.xiaoba.contants.ElasticSearchContants;
import com.xiaoba.entity.Essay;
import com.xiaoba.entity.EssayIndex;
import com.xiaoba.service.ElasticSearchService;
import com.xiaoba.util.PathLoadUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
    public EssayIndex buildEssayIndex(Essay essay) {
        EssayIndex essayIndex=new EssayIndex();
        essayIndex.setEssayId(essay.getEssayId());
        essayIndex.setEssayTitle(essay.getEssayTitle());
        essayIndex.setEssayAbstract(essay.getEssayAbstract());
        essayIndex.setEssayAuthor(essay.getEssayAuthor());
        essayIndex.setEssayPublishTime(essay.getEssayPublishTime());
        essayIndex.setSavePath(essay.getSavePath());
        return essayIndex;
    }

    @Override
    public Map<String,Object> search(String keyWord, int currentPage) throws IOException {
        if(currentPage<=0){
            currentPage=0;
        }

        //条件搜索
        SearchRequest request=new SearchRequest(ElasticSearchContants.INDEX);
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();

        //分页
        sourceBuilder.from(currentPage);
        sourceBuilder.size(ElasticSearchContants.PAGE_SIZE);

        //查找
        QueryStringQueryBuilder queryBuilder= new QueryStringQueryBuilder(keyWord);
        queryBuilder.field(ElasticSearchContants.ESSAY_TITLE)
                .field(ElasticSearchContants.ESSAY_ABSTRACT)
                .field(ElasticSearchContants.ESSAY_AUTHOR);
        sourceBuilder.query(queryBuilder);
        //延时
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //高光功能暂且搁置
        //sourceBuilder.highlighter();

        //执行搜索
        request.source(sourceBuilder);
        SearchResponse response= restHighLevelClient.search(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());
        //解析结果
        List<Map<String,Object>> list=new ArrayList<>();
        for(SearchHit documentFields:response.getHits().getHits()){
            Map<String,Object> map=documentFields.getSourceAsMap();
            map.remove("@version");
            map.remove("@timestamp");

            map.replace("savaPath", PathLoadUtil.loadEssay((String)map.get("savePath")));
            list.add(map);
        }
        int size=(int)response.getHits().getTotalHits().value;
        int pageNum=size/ElasticSearchContants.PAGE_SIZE+1;
        System.out.println(pageNum);
        //装载返回结果
        Map<String,Object> result =new HashMap<>();
        //当前页的页数
        result.put(ElasticSearchContants.CURRENT_PAGE,currentPage);
        //总的页数
        result.put(ElasticSearchContants.PAGE_NUM,pageNum);
        //搜索结果
        result.put(ElasticSearchContants.SEARCH_LIST,list);
        System.out.print(result);
        return result;
    }
}
