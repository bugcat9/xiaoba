package com.xiaoba.xiaobasearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "xiaoba",type = "essayindex")
public class EssayIndex {

    @Id
    private Integer essayId;

    @Field(type = FieldType.Auto,analyzer = "ik_max_word")
    private String essayTitle;
    private String essayAbstract;
    private String essayAuthor;
    private Date essayPublishTime;
    private String savePath;

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    public String getEssayTitle() {
        return essayTitle;
    }

    public void setEssayTitle(String essayTitle) {
        this.essayTitle = essayTitle;
    }

    public String getEssayAbstract() {
        return essayAbstract;
    }

    public void setEssayAbstract(String essayAbstract) {
        this.essayAbstract = essayAbstract;
    }

    public String getEssayAuthor() {
        return essayAuthor;
    }

    public void setEssayAuthor(String essayAuthor) {
        this.essayAuthor = essayAuthor;
    }

    public Date getEssayPublishTime() {
        return essayPublishTime;
    }

    public void setEssayPublishTime(Date essayPublishTime) {
        this.essayPublishTime = essayPublishTime;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
