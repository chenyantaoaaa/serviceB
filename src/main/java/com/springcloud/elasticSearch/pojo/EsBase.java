package com.springcloud.elasticSearch.pojo;

/**
 * Created by yantao.chen on 2018-08-13.
 */
public class EsBase {
    private String index;

    private String type;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
