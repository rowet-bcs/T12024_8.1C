package com.example.task81c;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Chat {
    // Custom chat class to store chat POST call data
    String userMessage;
    ArrayList<Dictionary> chatHistory;

    public Chat(String userMessage) {
        this.userMessage = userMessage;
        this.chatHistory = new ArrayList<>();
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public ArrayList<Dictionary> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ArrayList<Dictionary> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public void addChatHistory(String user, String llama){
        // Store data in chat history list
        Dictionary history = new Hashtable<>();

        history.put("User", user);
        if (llama == null){
            history.put("Llama", "No response received");
        } else {
            history.put("Llama", llama);
        }

        this.chatHistory.add(history);
    }
}
