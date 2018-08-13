package com.springcloud.elasticSearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springcloud.elasticSearch.ESConstant;
import com.springcloud.elasticSearch.pojo.Book;
import com.springcloud.util.JsonUtils;
import net.sf.json.JSONObject;

/**
 * Created by yantao.chen on 2018-08-13.
 */
@Service
public class BookService {
    @Autowired
    private RestTemplate restTemplate;

    public String getInfo(Book book){
        StringBuffer queryUrl = new StringBuffer();
        queryUrl.append(ESConstant.ES_URL+"/"+book.getIndex()+"/"+ book.getType()+"/_search");
        JSONObject query = JsonUtils.obj2EsParamsNoNull(book);
        String result = restTemplate.postForEntity(queryUrl.toString(),query, String.class).getBody();
        return result;
    }
}
