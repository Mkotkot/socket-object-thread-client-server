package com.connection;

import com.jab.JabberMessage;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
    private final Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public void processMessage(JabberMessage message) {
        PrintWriter writer;
        try {
//            writer = new PrintWriter(new OutputStreamWriter(
//                    socket.getOutputStream()), true);
//            writer.println(message.toString());
            ObjectOutputStream objectInputStream = new ObjectOutputStream(socket.getOutputStream());
//            writer = new PrintWriter(new OutputStreamWriter(
//                    socket.getOutputStream()), true);
//            writer.println(MessageFlag.pureMessage + message);
            objectInputStream.writeObject(message);
            objectInputStream.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}