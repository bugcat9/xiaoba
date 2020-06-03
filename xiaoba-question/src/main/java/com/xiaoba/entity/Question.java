package com.xiaoba.entity;

import java.util.Date;

public class Question {
    private Integer questionId;
    private String questionerName;
    private Date questionTime;
    private String questionContent;
    private Integer answerNum;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionerName() {
        return questionerName;
    }

    public void setQuestionerName(String questionerName) {
        this.questionerName = questionerName;
    }

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionerName='" + questionerName + '\'' +
                ", questionTime=" + questionTime +
                ", questionContent='" + questionContent + '\'' +
                ", answerNum=" + answerNum +
                '}';
    }
}
