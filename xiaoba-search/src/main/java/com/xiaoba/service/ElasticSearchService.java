package com.xiaoba.service;

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
     * @param keyWord
     * @param currentPage
     * @return
     * @throws IOException
     */
     Map<String,Object> searchEssay(String keyWord,int currentPage) throws IOException;

    /**
     * 搜索问题
     * @param keyWord
     * @param currentPage
     * @return
     * @throws IOException
     */
     Map<String,Object> searchQuestion(String keyWord,int currentPage) throws IOException;

    /**
     * 删除文章索引
     * @param essayId 文章ID
     */
     void deleteEssayIndex(Integer essayId);

    /**
     * 删除问题索引
     * @param questionId 问题ID
     */
     void deleteQuestionIndex(Integer questionId);
}
