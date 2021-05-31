package com.connection;

import com.jab.JabberMessage;

public interface MessageHandler {
    void onReceive(Connection connection, JabberMessage message);
}