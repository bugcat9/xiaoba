package com.xiaoba.util;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhouning
 */
@Component
public class RabbitMqUtils {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * 发送到指定Queue
     * @param queueName
     * @param obj
     */
    public void send(String queueName, Object obj){
        this.rabbitTemplate.convertAndSend(queueName, obj);
    }

    public Object receive(String queueName){
        return this.rabbitTemplate.receiveAndConvert(queueName);
    }

    public void createQueue(String queueName){
        //创建路由交换
        amqpAdmin.declareExchange( new DirectExchange( queueName+".direct",true,false));
        //创建Queue
        amqpAdmin.declareQueue(new Queue(queueName,true));
        //绑定Queue
        amqpAdmin.declareBinding(new Binding(queueName,Binding.DestinationType.QUEUE,queueName+".direct",queueName,null));
    }


}
