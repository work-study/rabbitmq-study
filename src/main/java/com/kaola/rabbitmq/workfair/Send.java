package com.kaola.rabbitmq.workfair;

import com.kaola.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
    private static final String QUEUE_NAME="test_work_queue";
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //消费者发送确认之前，消息队列不发送下一个消息，限制发送给同一个消费者不超过一个消息
            channel.basicQos(1);
            for(int i=0;i<50;i++) {
                String msg="hello"+i;
                channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
                Thread.sleep(i*20);
            }
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
