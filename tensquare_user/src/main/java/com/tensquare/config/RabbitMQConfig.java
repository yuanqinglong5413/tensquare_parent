package com.tensquare.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * 创建发送短信的队列
     */
    @Bean
    public Queue getQueue() {
        return new Queue("sms");
    }
}
