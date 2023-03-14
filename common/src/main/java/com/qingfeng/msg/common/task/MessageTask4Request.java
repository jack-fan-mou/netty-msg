package com.qingfeng.msg.common.task;

import com.qingfeng.msg.common.protobuf.MessageBuilder;
import com.qingfeng.msg.common.protobuf.MessageModule;
import com.qingfeng.msg.common.protobuf.Result;
import com.qingfeng.msg.common.scanner.Invoker;
import com.qingfeng.msg.common.scanner.InvokerTable;
import io.netty.channel.ChannelHandlerContext;

public class MessageTask4Request implements Runnable {

    private ChannelHandlerContext ctx;

    private MessageModule.Message message;

    private final String RESULT = "-return";

    public MessageTask4Request(ChannelHandlerContext ctx, MessageModule.Message message) {
        this.ctx = ctx;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            String module = message.getModule();
            String cmd = message.getCmd();
            byte[] data = message.getBody().toByteArray();
            Invoker invoker = InvokerTable.getInvoker(module, cmd);
            Result<?> result = (Result<?>) invoker.invoke(data);

            ctx.writeAndFlush(MessageBuilder.getResponseMessage(module + RESULT, cmd + RESULT
                    , result.getResultType(),
                    result.getContent())
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
