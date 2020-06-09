package com.xiaoba.service;

import com.xiaoba.entity.Message;
import com.xiaoba.util.RabbitMqUtils;

import java.util.List;
import java.util.Map;

public interface MessageService {

    boolean sendMessage(String queueName, Object obj);

    boolean sendMessage(String sender,String receiver,String msg);

    Object sysMessage();

    boolean createQueue(String queueName);

    Object receiveMessage(String queueName);

    /**
     * 从数据库中得到数据
     * @param receiver
     * @return
     */
    List<Message> receiveMsg(String receiver,int pageIndex);
}
