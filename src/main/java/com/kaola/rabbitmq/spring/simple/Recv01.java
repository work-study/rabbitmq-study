package com.kaola.rabbitmq.spring.simple;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class Recv01 implements MessageListener {

    public void onMessage(Message message) {
        System.out.println("msg:"+new String(message.getBody()));
    }
}
