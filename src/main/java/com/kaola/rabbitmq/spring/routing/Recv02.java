package com.kaola.rabbitmq.spring.routing;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Recv02 implements MessageListener {

    public void onMessage(Message message) {
        System.out.println("Recv02 msg:"+new String(message.getBody()));
    }
}
