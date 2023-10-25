package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Server extends Thread {
    Socket connection;
    BufferedReader in;
    DataOutputStream out;
    Integer randomNumber;
    Integer attempts = 1;

    public Server(Socket newConnection) throws IOException {
        // server = new ServerSocket(port);
        // System.out.println("Server listening at port " + port);
        this.connection = newConnection;
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        out = new DataOutputStream(connection.getOutputStream());
        randomNumber = (int) (Math.random() * 100 + 1);
    }

    public void run() {
        try {

            String userInput = in.readLine();
            Integer procUserInput = Integer.parseInt(userInput);

            while (procUserInput != randomNumber) {
                System.out.println("User input: " + userInput);
                if (procUserInput > randomNumber) {
                    out.writeBytes("1\n");
                } else {
                    out.writeBytes("2\n");
                }

                userInput = in.readLine();
                procUserInput = Integer.parseInt(userInput);

                attempts++;
            }
            out.writeBytes("3\n");
            out.writeBytes(attempts + "\n");
            connection.close();
        } catch (Exception e) {
            System.err.println("FUCK");
            System.exit(1);
        }
    }
}
