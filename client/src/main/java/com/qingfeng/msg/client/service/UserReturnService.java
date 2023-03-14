package com.qingfeng.msg.client.service;

import com.qingfeng.msg.common.annotation.Cmd;
import com.qingfeng.msg.common.annotation.Module;
import com.qingfeng.msg.common.protobuf.MessageModule;
import org.springframework.stereotype.Service;

@Service
@Module(module = "user-return")
public class UserReturnService {
    @Cmd(cmd = "save-return")
    public void saveReturn(MessageModule.ResultType resultType, byte[] data){
        if(MessageModule.ResultType.SUCCESS.equals(resultType)){
            System.out.println("处理 user save 方法成功");
        }else{
            System.out.println("处理 user save 方法失败");
        }

    }

    @Cmd(cmd = "update-return")
    public void updateReturn(MessageModule.ResultType resultType, byte[] data){
        if(MessageModule.ResultType.SUCCESS.equals(resultType)){
            System.out.println("处理 user update 方法成功");
        }else{
            System.out.println("处理 user update 方法失败");
        }

    }
}
