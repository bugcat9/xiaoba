package com.xiaoba.service;

import com.xiaoba.entity.EssayIndex;
import com.xiaoba.entity.Essay;

import java.io.IOException;
import java.util.Map;


/**
 * @author 王文旭
 * @date 2020/6/1
 */

public interface ElasticSearchService {

    public EssayIndex buildEssayIndex(Essay essay);

    public Map<String,Object> search(String keyWord, int currentPage) throws IOException;
}
