package com.zj802.smks.config;

import com.zj802.smks.server.ClientStarter;
import com.zj802.smks.server.ServerStarter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Devin
 **/
@Configuration
public class WebsocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        System.out.println("server start");
        new Thread(new ServerStarter()).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new ClientStarter()).start();
        return new ServerEndpointExporter();
    }
}
