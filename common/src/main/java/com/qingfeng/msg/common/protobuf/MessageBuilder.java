package com.qingfeng.msg.common.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageV3;

public class MessageBuilder {

    public static final int CRC_CODE = 0xabef1314;

    public static MessageModule.Message getRequestMessage(String module, String cmd, GeneratedMessageV3 data){
        return MessageModule.Message.newBuilder()
                .setModule(module)
                .setCmd(cmd)
                .setCrcCode(CRC_CODE)
                .setMessageType(MessageModule.MessageType.REQUEST)
                .setBody(ByteString.copyFrom(data.toByteArray()))
                .build();
    }

    public static MessageModule.Message getResponseMessage(String module, String cmd, MessageModule.ResultType resultType, GeneratedMessageV3 data){
        return MessageModule.Message.newBuilder()
                .setModule(module)
                .setCmd(cmd)
                .setCrcCode(CRC_CODE)
                .setMessageType(MessageModule.MessageType.RESPONSE)
                .setResultType(resultType)
                .setBody(ByteString.copyFrom(data.toByteArray()))
                .build();
    }
}
