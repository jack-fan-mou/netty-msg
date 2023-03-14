package com.qingfeng.msg.client;

import com.qingfeng.msg.common.protobuf.MessageModule;
import com.qingfeng.msg.common.task.MessageTask4Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<MessageModule.Message> {
    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
            10, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.AbortPolicy());
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageModule.Message message) throws Exception {
        log.info("channelRead0 module={}, cmd={}", message.getModule(), message.getCmd());
        poolExecutor.submit(new MessageTask4Response(ctx, message));
    }

    public void channelRead1(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("come in channelRead method ");
        MessageModule.Message message = (MessageModule.Message) msg;
        poolExecutor.submit(new MessageTask4Response(ctx, message));
    }
}
