package com.qingfeng.msg.server;

import com.qingfeng.msg.common.protobuf.MessageModule;
import com.qingfeng.msg.common.task.MessageTask4Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ServerHandler extends SimpleChannelInboundHandler<MessageModule.Message> {
    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
            10, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.AbortPolicy());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageModule.Message message) throws Exception {
        System.out.println(message);
        poolExecutor.submit(new MessageTask4Request(ctx, message));
    }

    public void channelRead1(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageModule.Message message = (MessageModule.Message) msg;
        poolExecutor.submit(new MessageTask4Request(ctx, message));
    }
}
