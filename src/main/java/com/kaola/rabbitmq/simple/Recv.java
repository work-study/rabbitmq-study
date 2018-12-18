package com.kaola.rabbitmq.simple;

import com.kaola.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;


public class Recv {
    private static final String QUEUE_NAME="test_simple_queue";
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            //oldRecv(channel);
            //队列声明
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //定义消费者消费逻辑
            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msgString  = new String(body);
                    System.out.println("msg:"+msgString);
                }
            };
            //监听队列
            channel.basicConsume(QUEUE_NAME,true,consumer);
            /*channel.close();
            connection.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void oldRecv(Channel channel) throws IOException, InterruptedException {
        //定义队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            byte[] body = delivery.getBody();
            System.out.println("msg:"+new String(body));
        }
    }
}
