package com.xiaoba.entity;

import lombok.Data;

@Data
public class message {

    private Integer messageId;
    private String messageSender;
    private String messageReciver;
    private Integer messageStatus;
}
