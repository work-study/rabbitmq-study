package com.kaola.rabbitmq.workfair;

import com.kaola.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv02 {
    private static final String QUEUE_NAME="test_work_queue";
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            final Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            channel.basicQos(1);
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
                        channel.basicAck(envelope.getDeliveryTag(),false);
                    }
                }
            };
            boolean autoAck=false;
            channel.basicConsume(QUEUE_NAME,autoAck,consumer);
            /*channel.close();
            connection.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
