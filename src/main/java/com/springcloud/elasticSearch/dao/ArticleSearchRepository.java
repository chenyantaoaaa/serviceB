package com.springcloud.elasticSearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.springcloud.elasticSearch.pojo.Article;

/**
 * Created by yantao.chen on 2018-08-14.
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article,Long>{
}
