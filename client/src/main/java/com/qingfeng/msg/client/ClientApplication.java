package com.qingfeng.msg.client;

import com.qingfeng.msg.client.listener.ApplicationListenerReadyEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {

        final SpringApplication app =  new SpringApplication(ClientApplication.class);
        app.addListeners(new ApplicationListenerReadyEvent());
        app.run(args);
    }

}
