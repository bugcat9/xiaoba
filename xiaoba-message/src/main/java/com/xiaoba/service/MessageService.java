package com.xiaoba.service;

import com.xiaoba.entity.Message;
import com.xiaoba.util.RabbitMqUtils;

import java.util.List;
import java.util.Map;

/**
 * 消息service
 * @author zhouning
 */
public interface MessageService {

    /**
     * 向队列发送消息，会存在消息队列中
     * @param queueName
     * @param obj
     * @return
     */
    boolean sendMessage(String queueName, Object obj);

    /**
     * 向用户发送信息，会存在数据库中
     * @param sender
     * @param receiver
     * @param msg
     * @return
     */
    boolean sendMessage(String sender,String receiver,String msg);

    /**
     * 得到系统消息
     * @return
     */
    Object sysMessage();

    /**
     * 创建队列
     * @param queueName
     * @return
     */
    boolean createQueue(String queueName);

    /**
     * 得到消息
     * @param queueName
     * @return
     */
    Object receiveMessage(String queueName);

    /**
     * 从数据库中得到已读消息
     * @param receiver
     * @param pageIndex 页数，从0开始
     * @return
     */
    List<Message> receiveReadMsg(String receiver,int pageIndex);

    /**
     * 从数据库中得到未读消息
     * @param receiver
     * @param pageIndex 页数，从0开始
     * @return
     */
    List<Message> receiveUnReadMsg(String receiver,int pageIndex);

    /**
     * 已读消息数量
     * @param receiver
     * @return
     */
    int countOfReadMsg(String receiver);

    /**
     * 未读消息数量
     * @param receiver
     * @return
     */
    int countOfUnReadMsg(String receiver);
}
