package com.springcloud.elasticSearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.elasticSearch.pojo.EsBase;
import com.springcloud.elasticSearch.service.BookService;

/**
 * Created by yantao.chen on 2018-08-13.
 */
@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("getInfo")
    public String getInfo(@RequestBody EsBase esBase){
        return bookService.getInfo(esBase);
    }
}
