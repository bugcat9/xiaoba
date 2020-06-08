package com.xiaoba.config;

import com.xiaoba.constans.RabbitMqConstants;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

/**
 * @author zhouning
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 创建系统
     * @return
     */
    @Bean
    public Queue queue(){

        return new Queue(RabbitMqConstants.SYS_QUEUE,true);
    }
}
