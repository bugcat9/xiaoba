package com.xiaoba.repository;

import com.xiaoba.entity.QuestionIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuestionRepository extends ElasticsearchRepository<QuestionIndex,Integer> {
}
