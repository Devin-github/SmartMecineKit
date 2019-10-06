package com.zj802.smks.entity;




import com.zj802.smks.server.ServerStarter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author Devin
 */
//上位机与下位机通信的封装socket

public class SMKSocket {
    private Socket socket;
    private BufferedReader br;
    private PrintStream ps;



    public SMKSocket(Socket socket){
        try {
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.ps = new PrintStream(socket.getOutputStream());
            this.socket = socket;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readMessage(){
        try {
            String msg =  this.br.readLine();
            if(msg != null){
                return msg;
            }else {

                ServerStarter.smkSockets.get("stm32").remove(this);
                System.out.println("remove ");
                this.socket.close();
                return "close";
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }
    public void sendMessage(String msg){
        this.ps.println(msg);
    }


}
