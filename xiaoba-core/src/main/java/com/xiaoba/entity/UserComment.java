package com.xiaoba.entity;

import lombok.Data;

import java.sql.Date;


/**
 *
 * @author zhouning
 */
@Data
public class UserComment {
    /**
     *  评论唯一标识
     */
    private Integer commentId;

    /**
     * 0表示文章，1表示问题，2表示回答，3表示父评论（即属于哪个评论下的子评论）
     */
    private Integer parentType;

    /**
     * 根据父对象类型和父对象id定位该评论
     */
    private Integer parentId;

    /**
     * 表示哪个用户发表了该评论，用户与评论为一对N的关系
     */
    private String commentatorName;

    private Date commentTime;

    private String commentContent;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getParentType() {
        return parentType;
    }

    public void setParentType(Integer parentType) {
        this.parentType = parentType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCommentatorName() {
        return commentatorName;
    }

    public void setCommentatorName(String commentatorName) {
        this.commentatorName = commentatorName;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
