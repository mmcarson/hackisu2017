package com.example.sabishop.veloxopus;

import java.util.ArrayList;
import java.util.SortedMap;

/**
 * Created by marissacarson on 2/25/17.
 */

public class ChatConversation {
    private long chatID, workerID, jobID;
    private ArrayList<ChatLog> chatList;

    public long getChatID() {
        return chatID;
    }

    public void setChatID(long chatID) {
        this.chatID = chatID;
    }

    public long getWorkerID() {
        return workerID;
    }

    public void setWorkerID(long workerID) {
        this.workerID = workerID;
    }

    public long getJobID() {
        return jobID;
    }

    public void setJobID(long jobID) {
        this.jobID = jobID;
    }

    public ArrayList<ChatLog> getChatList() {
        return chatList;
    }

    public void addToChatList(ChatLog chat){
        chatList.add(chat);
    }

    public ChatConversation(long chatID, long workerID, long jobID, ArrayList<ChatLog> chatList) {
        this.chatID = chatID;
        this.workerID = workerID;
        this.jobID = jobID;
        this.chatList = chatList;
    }
}
