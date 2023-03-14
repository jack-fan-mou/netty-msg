package com.qingfeng.msg.server;

import com.qingfeng.msg.server.listener.ApplicationListenerReadyEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServerApplication.class);
        application.addListeners(new ApplicationListenerReadyEvent());
        application.run(args);
    }

}
