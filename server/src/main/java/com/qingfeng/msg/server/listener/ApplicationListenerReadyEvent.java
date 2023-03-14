package com.qingfeng.msg.server.listener;

import com.qingfeng.msg.server.Server;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationListenerReadyEvent implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("-------------Server应用服务启动成功-------------------");
        Server server = new Server();
    }
}
