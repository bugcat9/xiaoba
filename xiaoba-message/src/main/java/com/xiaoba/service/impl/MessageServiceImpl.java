package com.xiaoba.service.impl;

import com.xiaoba.constans.RabbitMqConstants;
import com.xiaoba.service.MessageService;
import com.xiaoba.util.RabbitMqUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    RabbitMqUtils rabbitMqUtils;

    @Override
    public boolean sendMessage(String queueName, Object obj) {
        try{
            rabbitMqUtils.send(queueName,obj);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Object sysMessage() {
        return rabbitMqUtils.receive(RabbitMqConstants.SYS_QUEUE);
    }

    @Override
    public boolean createQueue(String queueName) {
        try{
            rabbitMqUtils.createQueue(queueName);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Object receiveMessage(String queueName) {
        return rabbitMqUtils.receive(queueName);
    }

}
