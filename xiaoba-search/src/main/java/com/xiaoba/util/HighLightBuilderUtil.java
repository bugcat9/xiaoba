package com.xiaoba.util;

import com.xiaoba.contants.ElasticSearchContants;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HighLightBuilderUtil {

    /**
     * 构造HighlightBuilder对象
     * @param fields 搜索字段
     * @return HighlightBuilder对象
     */
    public static HighlightBuilder buildHighLightBuilder(String[] fields){
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        for(String filed:fields){
            highlightBuilder.field(filed);
        }

        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        return highlightBuilder;
    }


    /**
     * 解析，替换搜索结果中的匹配部分为高亮
     * @param highlightFieldMap 高亮的
     * @param sourceAsMap 普通的
     * @param field 替换的搜素字段
     */
    public static void parseHighLight(Map<String,HighlightField> highlightFieldMap,Map<String,Object> sourceAsMap,String field){
        HighlightField highlightField=highlightFieldMap.get(field);
        if(highlightField!=null){
            Text[] fragments=highlightField.fragments();
            StringBuilder content= new StringBuilder();
            for(Text text:fragments){
                content.append(text);
            }
            sourceAsMap.replace(field, content.toString());
        }
    }

    /**
     * 构造搜索请求
     * @param index 索引名
     * @param fileds 搜索哪些字段
     * @param keyWord 搜索关键
     * @param currentPage 当前页
     * @return 搜索请求
     */
    public static SearchRequest buildSearchRequest(String index,String[] fileds,String keyWord,int currentPage){
        if(currentPage<=0){
            currentPage=0;
        }
        //条件搜索
        SearchRequest request=new SearchRequest(index);
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();

        //分页
        sourceBuilder.from(currentPage);
        sourceBuilder.size(ElasticSearchContants.PAGE_SIZE);

        //查找
        QueryStringQueryBuilder queryBuilder= new QueryStringQueryBuilder(keyWord);
        for(String field:fileds){
            //搜索字段
            queryBuilder.field(field);
        }
        sourceBuilder.query(queryBuilder);
        //延时
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //高亮,HighLightBuilderUtil为本地编写的类
        HighlightBuilder highlightBuilder= HighLightBuilderUtil.buildHighLightBuilder(fileds);
        sourceBuilder.highlighter(highlightBuilder);
        //执行搜索
        request.source(sourceBuilder);
        return request;
    }

    /**
     * 解析应答
     * @param response SearchResponse对象
     * @param fields 搜索的字段，String[]
     * @param index 索引名，作用是根据索引名可以对不同搜索的对象进行不同的过滤，然而这中方式不合适，将就
     * @param currentPage 当前页
     * @return 搜索结果
     */
    public static Map<String,Object> parseResponse(SearchResponse response,String[] fields,String index,int currentPage){
        //解析结果
        List<Map<String,Object>> list=new ArrayList<>();
        for(SearchHit hit:response.getHits().getHits()){
            //解析高亮
            Map<String, HighlightField> highlightFieldMap= hit.getHighlightFields();
            Map<String,Object> map=hit.getSourceAsMap();
            //解析标题
            for(String filed:fields){
                //解析高亮字段
                parseHighLight(highlightFieldMap,map,filed);
            }
            //去除不需要的内容
            map.remove("@version");
            map.remove("@timestamp");
            map.remove("type");
            if(ElasticSearchContants.ESSAY_INDEX.equals(index)){
                String time=(String)map.get("essayPublishTime");
                time=time.substring(0,time.length()-5).replace('T',' ');
                map.replace("essayPublishTime",time);
                map.replace("savePath", PathLoadUtil.loadEssay((String)map.get("savePath")));
            }
            else if(ElasticSearchContants.QUESTION_INDEX.equals(index)){
                String time=(String)map.get("questionTime");
                time=time.substring(0,time.length()-5).replace('T',' ');
                map.replace("questionTime",time);
                map.replace("savePath",PathLoadUtil.loadQuestion((String)map.get("savePath")));
            }
            list.add(map);
        }

        int size=(int)response.getHits().getTotalHits().value;
        //int pageNum=size/ElasticSearchContants.PAGE_SIZE+1;

        //装载返回结果
        Map<String,Object> result =new HashMap<>();
        //当前页的页数
        result.put(ElasticSearchContants.CURRENT_PAGE,currentPage);
        //总的结果数
        result.put(ElasticSearchContants.COUNT,size);
        //搜索结果
        result.put(ElasticSearchContants.SEARCH_LIST,list);
        System.out.print(result);
        return result;
    }

}
