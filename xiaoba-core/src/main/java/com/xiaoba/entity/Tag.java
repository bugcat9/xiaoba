package com.xiaoba.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author zhouning
 */
@Data
@ApiModel(value="tag对象", description="标签")
public class Tag {

    @ApiModelProperty(value = "标签id")
    Integer tagId;
    @ApiModelProperty(value = "标签名字")
    String tagName;


    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
