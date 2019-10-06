package com.zj802.smks.server;

import com.zj802.smks.entity.SMKSocket;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;



public class ServerStarter implements Runnable{
    public static HashMap<String,ArrayList<SMKSocket>> smkSockets;
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(12356);
            int i = 0;
            while (true){
                Socket socket = serverSocket.accept();
                SMKSocket smkSocket = new SMKSocket(socket);
                ArrayList<SMKSocket> smk1 = new ArrayList<>();
                if(i == 0){
                    smkSockets = new HashMap<>();
                    ArrayList<SMKSocket> smk = new ArrayList<>();
                    smk.add(smkSocket);
                    smkSockets.put("web",smk);
                    i = 1;
                    System.out.println("connect first");
                    new Thread(new ServerRunner(smkSocket,"web")).start();

                }else {
                    smk1.add(smkSocket);
                    smkSockets.put("stm32",smk1);
                    System.out.println("connect");
                    new Thread(new ServerRunner(smkSocket,"stm32")).start();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
