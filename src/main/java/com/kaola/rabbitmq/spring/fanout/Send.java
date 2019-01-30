package com.kaola.rabbitmq.spring.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Send {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("fanout/spring_send.xml");
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        System.out.println("rabbitTemplate:"+rabbitTemplate);
        rabbitTemplate.convertAndSend("hello world");
        applicationContext.destroy();
    }
}
