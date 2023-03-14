package com.qingfeng.msg.client.listener;

import com.qingfeng.msg.client.Client;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationListenerReadyEvent implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("-------------Client应用服务启动成功-------------------");
        Client.getInstance().init();
    }
}
