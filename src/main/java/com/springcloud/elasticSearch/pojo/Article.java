package com.springcloud.elasticSearch.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by yantao.chen on 2018-08-14.
 */
@Document(indexName = "book",type = "article",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class Article {
    @Id
    private Long id;

    private String name;

    private String fate;

    private String remark;

    private String page;

    private String tag;

    private String hot;

    private String valid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFate() {
        return fate;
    }

    public void setFate(String fate) {
        this.fate = fate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fate='" + fate + '\'' +
                ", remark='" + remark + '\'' +
                ", page='" + page + '\'' +
                ", tag='" + tag + '\'' +
                ", hot='" + hot + '\'' +
                ", valid='" + valid + '\'' +
                '}';
    }
}
