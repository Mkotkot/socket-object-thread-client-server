package com.connection;

import com.jab.JabberMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

class ConnectionThread extends Thread {
    private final Socket socket;
    private final SocketServer socketServer;
    private final Connection connection;
    private boolean isRunning;

    public ConnectionThread(Socket socket, SocketServer socketServer) {
        this.socket = socket;
        this.socketServer = socketServer;
        connection = new Connection(socket);
        isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            // Check whether the socket is closed.
            if (socket.isClosed()) {
                isRunning = false;
                break;
            }

            try {
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream ois = new
                        ObjectInputStream(inputStream);
                JabberMessage message = (JabberMessage) ois.readObject();

                if (message != null) {
                    if (!message.getMessage().equals("")) {
                        socketServer.getMessageHandler().onReceive(connection, message);
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void stopRunning() {
        isRunning = false;
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}