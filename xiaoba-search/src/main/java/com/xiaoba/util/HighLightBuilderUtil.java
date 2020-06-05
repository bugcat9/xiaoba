package com.xiaoba.util;

import com.xiaoba.contants.ElasticSearchContants;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.util.Map;

public class HighLightBuilderUtil {

    /**
     * 构造一个HighlightBuilder对象
     * @return HighlightBuilder
     */
    public static HighlightBuilder buildHighLightBuilder(){
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field(ElasticSearchContants.ESSAY_TITLE)
                .field(ElasticSearchContants.ESSAY_ABSTRACT)
                .field(ElasticSearchContants.ESSAY_AUTHOR);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        return highlightBuilder;
    }


    public static String replaceByHighLightField(HighlightField highlightField){
        if(highlightField!=null){
            Text[] fragments=highlightField.fragments();
            String field="";
            for(Text text:fragments){
                field+=text;
            }
            return field;
        }
        return null;
    }

    public static void parseHighLight(Map<String,HighlightField> highlightFieldMap, Map<String,Object> sourceAsMap, String field){
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
}
