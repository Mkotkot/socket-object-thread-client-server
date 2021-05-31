package com.connection;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer {
    private ServerSocket serverSocket;
    private ThreadListener threadListener;
    private MessageHandler messageHandler;

    public SocketServer(int port, MessageHandler handler) {
        messageHandler = handler;
        try {
            serverSocket = new ServerSocket(port);
            threadListener = new ThreadListener(this, serverSocket);
            threadListener.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(MessageHandler handler) {
        messageHandler = handler;
    }


    public void close() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                threadListener.stopRunning();
                threadListener.suspend();
                threadListener.stop();

                serverSocket.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}