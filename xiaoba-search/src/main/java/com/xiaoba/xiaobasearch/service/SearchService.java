package com.xiaoba.xiaobasearch.service;

import com.xiaoba.entity.Essay;
import com.xiaoba.xiaobasearch.entity.EssayIndex;
import com.xiaoba.xiaobasearch.repository.EssayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.Map;

/**
 *
 */
public interface SearchService {

    /**
     *
     * @return
     */
   public EssayIndex buildEssay(Essay essay);

    /**
     *
     * @param keywords
     * @return
     */
   public Map<String, Object> search(String keywords, Integer currentPage, Integer pageSize);

    
}
