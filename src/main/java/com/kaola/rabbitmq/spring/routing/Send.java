package com.kaola.rabbitmq.spring.routing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Send {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("routing/spring_send.xml");
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        System.out.println("rabbitTemplate:"+rabbitTemplate);
        rabbitTemplate.convertAndSend("info","hello world");
        applicationContext.destroy();
    }
}
