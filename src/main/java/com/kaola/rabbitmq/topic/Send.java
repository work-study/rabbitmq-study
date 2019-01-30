package com.kaola.rabbitmq.topic;

import com.kaola.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String exchange = "test_exchange_topic";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //申请exchange
        channel.exchangeDeclare(exchange,"topic");
        String msg = "hello";
        String routingKey="goods.delete";
        try {
            channel.txSelect();
            channel.basicPublish(exchange,routingKey,null,msg.getBytes());
            channel.txCommit();
        } catch (IOException e) {
            channel.txRollback();
        }
        System.out.println("msg:"+msg);
        channel.close();
        connection.close();
    }
}
