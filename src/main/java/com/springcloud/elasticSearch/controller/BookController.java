package com.springcloud.elasticSearch.controller;

import com.springcloud.elasticSearch.pojo.Book;
import com.springcloud.elasticSearch.service.BookService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yantao.chen on 2018-08-13.
 */
@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("getBookInfo")
    public String getInfo(@RequestBody Book book){
        String url = "http://127.0.0.1:5013/book/testBook";
//        JSONObject query = JsonUtils.obj2EsParamsNoNull(book);
        JSONObject obj = new JSONObject();
        obj.put("name",book.getName());
        obj.put("fate",book.getFate());
        String result = restTemplate.postForEntity(url,obj, String.class).getBody();
        return result;
//        return bookService.getInfo(book);
    }

    @RequestMapping("testBook")
    public String testBook(@RequestBody Book book){
        return bookService.getInfo(book);
    }
}
