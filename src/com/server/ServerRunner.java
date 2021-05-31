package com.server;

import com.connection.SocketServer;
import com.handeler.MessageListenerHandler;

import java.util.Scanner;

public class ServerRunner {
    private static final Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        startServer();


    }

    private static void startServer() {
        System.out.println("Start a server.");
        SocketServer server = new SocketServer(5556, new MessageListenerHandler());
        System.out.println("server is Running !");

        System.out.println("Please type anything and press enter to close the server...");
        inputScanner.nextLine();
        server.close();
    }
}
