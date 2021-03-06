package com.kaola.rabbitmq.spring.fanout;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Recv01 implements MessageListener {

    public void onMessage(Message message) {
        System.out.println("msg:"+new String(message.getBody()));
    }

    public static void main(String[] args) throws IOException {
        try {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("fanout/spring_recv.xml");
            applicationContext.start();
        } catch (BeansException e) {
            System.out.println(e);
        }
        System.in.read();

    }
}
