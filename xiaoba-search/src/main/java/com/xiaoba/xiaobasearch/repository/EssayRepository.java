package com.xiaoba.xiaobasearch.repository;

import com.xiaoba.xiaobasearch.entity.EssayIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EssayRepository extends ElasticsearchRepository<EssayIndex, String> {
}
