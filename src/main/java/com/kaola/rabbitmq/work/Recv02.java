package com.kaola.rabbitmq.work;

import com.kaola.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv02 {
    private static final String QUEUE_NAME="test_work_queue";
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            DefaultConsumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("msg[2]:"+new String(body));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println("done[2]");
                    }
                }
            };
            boolean autoAck=true;
            channel.basicConsume(QUEUE_NAME,autoAck,consumer);
            /*channel.close();
            connection.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
