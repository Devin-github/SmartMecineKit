package com.zj802.smks.server;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;


@ServerEndpoint(value = "/smks")
@Component
public class WebSocketServer {

    public static Map<String ,Session> webSessions = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) throws Exception{
        System.out.println("websocket connect");
        webSessions.put(session.getId(),session);
    }

    @OnClose
    public void onClose(Session session) throws Exception{
        webSessions.remove(session.getId());
    }

//    @OnMessage
//    public void onMessage(String message,Session session) throws Exception{
//        if("ping".equals(message)){
//            session.getBasicRemote().sendText("pong");
//        }
//    }


}
