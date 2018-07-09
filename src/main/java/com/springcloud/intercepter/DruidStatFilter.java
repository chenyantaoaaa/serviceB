package com.springcloud.intercepter;

/**
 * Created by yantao.chen on 2018-07-09.
 */

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @Package: pterosaur.account.config.filter
 * @Description: 拦截druid监控请求
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        })
public class DruidStatFilter extends WebStatFilter {
}
