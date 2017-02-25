package com.example.sabishop.veloxopus;

/**
 * Created by marissacarson on 2/25/17.
 */

public class ChatLog {
    public long chatID, toID, fromID, chatOrderID;
    public String message;

    public ChatLog(long chatID, long toID, long fromID, long chatOrderID, String message) {
        this.chatID = chatID;
        this.toID = toID;
        this.fromID = fromID;
        this.chatOrderID = chatOrderID;
        this.message = message;
    }
}
