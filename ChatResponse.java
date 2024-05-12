package com.example.task81c;

public class ChatResponse {
    // Custom class to store Chat Response data
    String message;
    public ChatResponse(String message) {
        this.message = message;
    }

    public String getResponse() {
        return message;
    }
    public void setResponse(String message) {
        this.message = message;
    }

}
