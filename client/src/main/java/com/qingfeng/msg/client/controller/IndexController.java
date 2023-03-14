package com.qingfeng.msg.client.controller;



import com.qingfeng.msg.client.Client;
import com.qingfeng.msg.common.protobuf.UserModule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class IndexController {

    private String getDate(){
        return new Date().toString();
    }

    @GetMapping("/index")
    public String index(){
        return "index:" + getDate();
    }

    @GetMapping("/save")
    public String save(){
        Client.getInstance().sendMessage("user", "save", UserModule.User.newBuilder().setUserId("002").setUserName("zhangsan").build());
        return "save:" + getDate();
    }

    @GetMapping("/update")
    public String update(){
        Client.getInstance().sendMessage("user", "update", UserModule.User.newBuilder().setUserId("004").setUserName("lipeng").build());
        return "update:" + getDate();
    }
}
