package com.xiaoba.service;

import com.xiaoba.entity.Essay;
import com.xiaoba.entity.EssayIndex;

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
