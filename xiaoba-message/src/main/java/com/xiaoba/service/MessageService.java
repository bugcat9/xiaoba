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
     * 从数据库中得到已读消息
     * @param receiver
     * @return
     */
    List<Message> receiveReadMsg(String receiver,int pageIndex);

    /**
     * 从数据库中得到未读消息
     * @param receiver
     * @return
     */
    List<Message> receiveUnReadMsg(String receiver,int pageIndex);

    int countOfReadMsg(String receiver);

    int countOfUnReadMsg(String receiver);
}
