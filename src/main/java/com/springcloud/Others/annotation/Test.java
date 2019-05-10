package com.springcloud.Others.annotation;

import org.springframework.stereotype.Controller;

/**
 * Created by yantao.chen on 2018-10-15
 */
@TestAnnotation(id = 1,msg = "msg")
@Controller
public class Test {
    public static void main(String[] args) {

        boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);

        if ( hasAnnotation ) {
            TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);

            System.out.println("id:"+testAnnotation.id());
            System.out.println("msg:"+testAnnotation.msg());
        }

    }
}
