package com.connection;

import com.jab.JabberMessage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {
    private Socket socket;
    private String userName;

    public SocketClient(InetAddress ip, int port) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public SocketClient(String userName, InetAddress ip, int port) {
        try {
            socket = new Socket(ip, port);
            this.userName = userName;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void writeMessageData(String message) {
        try {
            ObjectOutputStream objectInputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream.writeObject(new JabberMessage(message));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public JabberMessage readMessageData() {
        BufferedReader reader;
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream ois = new
                    ObjectInputStream(inputStream);
            JabberMessage message = (JabberMessage) ois.readObject();
            return message;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }


    public void close() {
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream()), true);
            writer.println(MessageFlag.connectionClosed);

            if (socket != null && !socket.isClosed())
                socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}