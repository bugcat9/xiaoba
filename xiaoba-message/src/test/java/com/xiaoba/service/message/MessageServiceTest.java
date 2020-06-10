package com.xiaoba.service.message;

import com.xiaoba.entity.Message;
import com.xiaoba.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Test
    public void sendMessage() {
        messageService.sendMessage("user01","user02","消息内容");
    }



    @Test
    public void receiveUnReadMsg() {
        List<Message> messages= messageService.receiveUnReadMsg("zhouning",0);
        System.out.println(messages);

    }

    @Test
    public void receiveReadMsg() {
        List<Message> messages= messageService.receiveReadMsg("zhouning",0);
        System.out.println(messages);

    }


    @Test
    public void countOfReadMsg() {
        int count=messageService.countOfReadMsg("user02");
        System.out.println(count);
    }

    @Test
    public void countOfUnReadMsg() {
        int count=messageService.countOfUnReadMsg("user02");
        System.out.println(count);
    }
}