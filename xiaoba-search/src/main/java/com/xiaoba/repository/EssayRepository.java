package com.xiaoba.repository;

import com.xiaoba.entity.EssayIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wwx
 */
public interface EssayRepository extends ElasticsearchRepository<EssayIndex,Integer> {

}
