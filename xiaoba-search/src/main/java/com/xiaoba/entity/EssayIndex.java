package com.xiaoba.entity;
import com.xiaoba.contants.ElasticSearchContants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = ElasticSearchContants.INDEX,type = "_doc",shards = 1, replicas = 0)
public class EssayIndex {
    @Id
    private Integer essayId;
    private String essayTitle;
    private String essayAbstract;
    private String essayAuthor;
    private Date essayPublishTime;
    private String savePath;
    private Integer commentNum;


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

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }
}
