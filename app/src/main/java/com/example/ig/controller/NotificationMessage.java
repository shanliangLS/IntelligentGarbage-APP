package com.example.ig.controller;

public class NotificationMessage {
    private int logoPath;
    private String UserName;
    private String sentTime;
    private String msgContent;

    public NotificationMessage(int logoPath, String userName, String sentTime, String msgContent) {
        this.logoPath = logoPath;
        this.UserName = userName;
        this.sentTime = sentTime;
        this.msgContent = msgContent;
    }

    public int getLogoPath() {
        return logoPath;
    }

    public String getUserName() {
        return UserName;
    }

    public String getSentTime() {
        return sentTime;
    }

    public String getMsgContent() {
        return msgContent;
    }
}
