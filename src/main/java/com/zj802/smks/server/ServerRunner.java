package com.zj802.smks.server;

import com.zj802.smks.entity.SMKSocket;
import org.springframework.stereotype.Component;


public class ServerRunner implements Runnable {
    private SMKSocket smkSocket;
    private String status;


    public ServerRunner(SMKSocket smkSocket,String status){
        this.smkSocket = smkSocket;
        this.status = status;
    }

    public void run() {

        while (true){

            if("stm32".equals(this.status)){

                String msg = smkSocket.readMessage();
                if(!"close".equals(msg)) {
                    ServerStarter.smkSockets.get("web").get(0).sendMessage(msg);
                    smkSocket.sendMessage("pong");
                }else {
                    break;
                }
            }else {
                break;
            }

        }
    }
}
