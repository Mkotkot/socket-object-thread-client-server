package com.client;


import com.connection.SocketClient;

import java.net.InetAddress;
import java.util.Scanner;

public class ClientRunner {
    private static final Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        startClient();
    }

    private static void startClient() {
        System.out.println("Start a client.");
        SocketClient client = null;
        try {
            client = new SocketClient(InetAddress.getLocalHost(), 5556);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Client Is Running !");

        for (int i = 0; i < 10; i++) {
            System.out.println("Please type Command to send to the server...");
            String string = inputScanner.nextLine();
            client.writeMessageData(string);

            System.out.println("Got the following message from the server:");
            System.out.println(client.readMessageData().toString());
        }

        System.out.println("Please type anything and press enter to close the client...");
        inputScanner.nextLine();
        client.close();
    }
}
