package com.zj802.smks.server;

import com.zj802.smks.entity.SMKSocket;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.net.Socket;


public class ClientStarter implements Runnable{

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1",12356);
            SMKSocket smkSocket = new SMKSocket(socket);
            System.out.println("start ");
            while (true){
                String msg = smkSocket.readMessage();
                if(WebSocketServer.webSessions.size() > 0){
                    System.out.println("websocket+"+msg);
                    for(String key : WebSocketServer.webSessions.keySet()){
                        WebSocketServer.webSessions.get(key).getBasicRemote().sendText(msg);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
