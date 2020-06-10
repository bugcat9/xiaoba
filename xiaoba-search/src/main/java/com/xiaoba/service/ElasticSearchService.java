package com.xiaoba.service;

//import com.xiaoba.entity.EssayIndex;
import com.xiaoba.entity.Essay;

import java.io.IOException;
import java.util.Map;


/**
 * @author 王文旭
 * @date 2020/6/1
 */

public interface ElasticSearchService {

    /**
     * 搜索文章
     * @param keyWord 搜索关键字
     * @param currentPage 搜索当前页
     * @return Map的key包含结果list，当前页，以及总页数
     */
    public Map<String,Object> searchEssay(String keyWord,int currentPage) throws IOException;

    /**
     * 搜索问题
     * @param keyWord 搜索关键字
     * @param currentPage 搜索当前页
     * @return Map的key包含结果list，当前页，以及总页数
     */
    public Map<String,Object> searchQuestion(String keyWord,int currentPage) throws IOException;

    /**
     * 删除文章索引
     * @param essayId 文章ID
     */
    public void deleteEssayIndex(Integer essayId);

    /**
     * 删除问题索引
     * @param questionId 问题ID
     */
    public void deleteQuestionIndex(Integer questionId);
}
