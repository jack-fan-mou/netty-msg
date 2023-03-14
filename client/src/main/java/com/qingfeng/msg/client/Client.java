package com.qingfeng.msg.client;




import com.google.protobuf.GeneratedMessageV3;
import com.qingfeng.msg.common.protobuf.MessageBuilder;
import com.qingfeng.msg.common.protobuf.MessageModule;
import com.qingfeng.msg.common.protobuf.UserModule;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private static final Client INSTANCE  = new Client();

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 2345;

    private Channel channel;

    private Client(){

    }
    private AtomicBoolean isConnect = new AtomicBoolean(false);

    public static Client getInstance(){
        return INSTANCE;
    }

    public synchronized void init(){
        if(!isConnect.get()){
            try{
                connect(HOST, PORT);
                isConnect.set(true);
            }catch (Exception e){

            }
        }
    }

    private void connect(String host, int port) throws Exception{
        Bootstrap bootstrap = new Bootstrap();
        try {
            EventLoopGroup group = new NioEventLoopGroup();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            ch.pipeline().addLast(new ProtobufDecoder(MessageModule.Message.getDefaultInstance()));
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            this.channel = future.channel();
            future.channel().closeFuture().sync();
            System.out.println("Client start ... ");
        }finally {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        try {
                            connect(host, port);// 发起重连操作
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void sendMessage(String module, String cmd, GeneratedMessageV3 messageV3){
        this.channel.writeAndFlush(MessageBuilder.getRequestMessage(module, cmd, messageV3));
    }

}
