package com.qingfeng.msg.common.task;

import com.qingfeng.msg.common.protobuf.MessageModule;
import com.qingfeng.msg.common.scanner.Invoker;
import com.qingfeng.msg.common.scanner.InvokerTable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class MessageTask4Response implements Runnable {

    private ChannelHandlerContext ctx;

    private MessageModule.Message message;

    private final String RESULT = "-return";

    public MessageTask4Response(ChannelHandlerContext ctx, MessageModule.Message message) {
        this.ctx = ctx;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            String module = message.getModule();
            String cmd = message.getCmd();
            MessageModule.ResultType resultType = message.getResultType();
            byte[] data = message.getBody().toByteArray();
            Invoker invoker = InvokerTable.getInvoker(module, cmd);
            invoker.invoke(resultType, data);
        }finally {
            ReferenceCountUtil.release(message);
        }
    }
}
