package com.handeler;

import com.connection.Connection;
import com.connection.MessageHandler;
import com.handeler.service.JabberServiceImpl;
import com.jab.JabberMessage;

public class MessageListenerHandler implements MessageHandler {


    @Override
    public void onReceive(Connection connection, JabberMessage message) {
        System.out.println("Got a message from a client:");
        System.out.println(message);
        //todo will write my code hear according the command which come
        // test case signIn mohamed
        // return successful login by mohamed

        JabberMessage serverResult = new JabberServiceImpl().inputProcessText(message.getMessage());
        System.out.println("Send Response back to the client .");
        connection.processMessage(serverResult);


    }
}