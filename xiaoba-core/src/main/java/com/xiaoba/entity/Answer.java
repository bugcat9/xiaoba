package com.xiaoba.entity;


import lombok.Data;

import java.sql.Date;
@Data
public class Answer {

    private Integer answerId;
    private Integer questionId;
    private String answerer;
    private Date answerTime;
    private String savePath;
    private Integer commentNum;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswerer() {
        return answerer;
    }

    public void setAnswerer(String answerer) {
        this.answerer = answerer;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
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

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", questionId=" + questionId +
                ", answerer='" + answerer + '\'' +
                ", answerTime=" + answerTime +
                ", savePath='" + savePath + '\'' +
                ", commentNum=" + commentNum +
                '}';
    }
}
