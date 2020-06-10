package com.xiaoba.service.impl;

import com.xiaoba.constans.RabbitMqConstants;
import com.xiaoba.constans.SysConstants;
import com.xiaoba.entity.Message;
import com.xiaoba.mapper.MessageMapper;
import com.xiaoba.service.MessageService;
import com.xiaoba.util.RabbitMqUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
/**
 * @author zhouning
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    RabbitMqUtils rabbitMqUtils;

    @Autowired
    MessageMapper messageMapper;

    private static final int PAGE_SIZE =5;
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
    public boolean sendMessage(String sender, String receiver, String msg) {
        Message message = new Message();
        message.setMessageSender(sender);
        message.setMessageReceiver(receiver);
        message.setMessageStatus(SysConstants.MESSAGE_UNREAD);
        message.setContent(msg);
        java.util.Date date = new java.util.Date();
        message.setSendTime(new Date(date.getTime()));
        int res=0;
        res=messageMapper.insertMessage(message);


        return res==1;
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

    @Override
    public List<Message> receiveUnReadMsg(String receiver,int pageIndex) {
        List<Message> messages = messageMapper.selectMsgByRec(receiver,SysConstants.MESSAGE_UNREAD,pageIndex,PAGE_SIZE);
        for (Message message:messages) {
            if (message.getMessageStatus()==SysConstants.MESSAGE_UNREAD){
                //设置状态为已读
                messageMapper.updateStatus(message.getMessageId(), SysConstants.MESSAGE_READ);
            }
        }
        return messages;
    }

    @Override
    public List<Message> receiveReadMsg(String receiver, int pageIndex) {
        return messageMapper.selectMsgByRec(receiver,SysConstants.MESSAGE_READ,pageIndex,PAGE_SIZE);
    }

    @Override
    public int countOfReadMsg(String receiver) {
        return messageMapper.countOfMsg(receiver, SysConstants.MESSAGE_READ);
    }

    @Override
    public int countOfUnReadMsg(String receiver) {
        return messageMapper.countOfMsg(receiver, SysConstants.MESSAGE_UNREAD);
    }

}
