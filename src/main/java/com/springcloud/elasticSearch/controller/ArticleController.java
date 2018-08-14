package com.springcloud.elasticSearch.controller;

import java.util.Iterator;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.elasticSearch.dao.ArticleSearchRepository;
import com.springcloud.elasticSearch.pojo.Article;

/**
 * Created by yantao.chen on 2018-08-14.
 */
@RequestMapping("article")
@RestController
public class ArticleController {
    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    @RequestMapping("query")
    public void testSearch() {
        String queryString = "haha11";//搜索关键字
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<Article> searchResult = articleSearchRepository.search(builder);
        Iterator<Article> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
