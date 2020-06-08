package com.xiaoba.service;

import com.xiaoba.util.RabbitMqUtils;

import java.util.Map;

public interface MessageService {

    boolean sendMessage(String queueName, Object obj);

    Object sysMessage();

    boolean createQueue(String queueName);

    Object receiveMessage(String queueName);
}
