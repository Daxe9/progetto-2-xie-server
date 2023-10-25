package com.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            ArrayList clients = new ArrayList<Socket>(); 
            do {
                Server s = new Server(serverSocket.accept(), clients); 
                clients.add(s);
                s.start();
            } while (true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }
}
