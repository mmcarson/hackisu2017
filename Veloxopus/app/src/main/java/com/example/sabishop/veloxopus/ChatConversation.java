package com.example.sabishop.veloxopus;

import java.util.ArrayList;
import java.util.SortedMap;

/**
 * Created by marissacarson on 2/25/17.
 */

public class ChatConversation {
    public long chatID, workerID, jobID;
    public ArrayList<ChatLog> chatList;

    public ChatConversation(long chatID, long workerID, long jobID, ArrayList<ChatLog> chatList) {
        this.chatID = chatID;
        this.workerID = workerID;
        this.jobID = jobID;
        this.chatList = chatList;
    }
}
