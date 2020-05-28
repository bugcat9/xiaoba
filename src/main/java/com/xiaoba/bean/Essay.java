package com.xiaoba.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhouning
 */
public class Essay implements Serializable {
    private Integer essayId;
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

    public String getEssayTittle() {
        return essayTitle;
    }

    public void setEssayTittle(String essayTitle) {
        this.essayTitle = essayTitle;
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

    @Override
    public String toString() {
        return "Essay{" +
                "essayId=" + essayId +
                ", essayTitle='" + essayTitle + '\'' +
                ", essayAbstract='" + essayAbstract + '\'' +
                ", essayAuthor='" + essayAuthor + '\'' +
                ", essayPublishTime=" + essayPublishTime +
                ", savePath='" + savePath + '\'' +
                '}';
    }
}
