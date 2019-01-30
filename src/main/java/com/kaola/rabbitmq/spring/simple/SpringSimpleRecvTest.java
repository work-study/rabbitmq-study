package com.kaola.rabbitmq.spring.simple;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class SpringSimpleRecvTest {
    public static void main(String[] args) throws IOException {
        try {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("simple/spring_recv.xml");
            applicationContext.start();
        } catch (BeansException e) {
            System.out.println(e);
        }
        System.in.read();

    }
}
