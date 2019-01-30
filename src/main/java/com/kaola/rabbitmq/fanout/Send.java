package com.kaola.rabbitmq.fanout;

import com.kaola.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String exchange = "test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(exchange, "fanout");
        String msg = "hello";
        channel.basicPublish(exchange,"",null,msg.getBytes());
        System.out.println("msg:"+msg);
        channel.close();
        connection.close();
    }
}
