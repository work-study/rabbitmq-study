package com.kaola.rabbitmq.work;

import com.kaola.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String QUEUE_NAME="test_work_queue";
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
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
