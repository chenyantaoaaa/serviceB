package com.springcloud.elasticSearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springcloud.elasticSearch.ESConstant;
import com.springcloud.elasticSearch.pojo.EsBase;

/**
 * Created by yantao.chen on 2018-08-13.
 */
@Service
public class BookService {
    @Autowired
    private RestTemplate restTemplate;

    public String getInfo(EsBase esBase){
        StringBuffer queryUrl = new StringBuffer();
        queryUrl.append(ESConstant.ES_URL+"/"+esBase.getIndex()+"/"+ esBase.getType()+"/_search");
        String result = restTemplate.getForEntity(queryUrl.toString(),String.class).getBody();
        return result;
    }
}
