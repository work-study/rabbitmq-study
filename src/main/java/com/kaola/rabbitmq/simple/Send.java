package com.kaola.rabbitmq.simple;

import com.kaola.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
    private static final String QUEUE_NAME="test_simple_queue";
    public static void main(String[] args) {
        try {
            //建立连接
            Connection connection = ConnectionUtils.getConnection();
            //创建通道
            Channel channel = connection.createChannel();
            //创建对列
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);

            String msg="hello simple111";
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("msg---end");
            channel.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
