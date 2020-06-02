package com.xiaoba.entity;

import com.xiaoba.contants.ElasticSearchContants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author 王文旭
 * @date 2020/6/2
 */
@Document(indexName = ElasticSearchContants.INDEX,type = "_doc",shards = 1, replicas = 0)
public class EssayIndex {
    @Id
    private Integer essayId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String essayTitle;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String essayAbstract;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
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
