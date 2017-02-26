package com.example.sabishop.veloxopus;

/**
 * Created by marissacarson on 2/25/17.
 */

public class ChatLog {
    public long jobID, toID, fromID, messageID;
    public String message;

    public ChatLog(long jobID, long toID, long fromID, long messageID, String message) {
        this.jobID = jobID;
        this.toID = toID;
        this.fromID = fromID;
        this.messageID = messageID;
        this.message = message;
    }
}
