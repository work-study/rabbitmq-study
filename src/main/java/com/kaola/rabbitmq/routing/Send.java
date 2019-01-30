package com.kaola.rabbitmq.routing;

import com.kaola.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String exchange = "test_exchange_routing";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //申请exchange
        channel.exchangeDeclare(exchange,"direct");
        String msg = "hello";
        String routingKey="warning";
        channel.basicPublish(exchange,routingKey,null,msg.getBytes());
        System.out.println("msg:"+msg);
        channel.close();
        connection.close();
    }
}
