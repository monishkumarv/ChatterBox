package com.example.chatterbox.objects;

import java.util.List;

public class FriendModel {

    private String phoneNo;
    private DateTimeModel dateTime;
    private List<MessageModel> messages;
    private String lastMessage;
    private Boolean typingStatus;
    private Boolean isMsgRead;            //unread message

    public FriendModel(){}

    public FriendModel(String phoneNo, DateTimeModel dateTime, List<MessageModel> messages, String lastMessage, Boolean typingStatus, Boolean isMsgRead) {
        this.phoneNo = phoneNo;
        this.dateTime = dateTime;
        this.messages = messages;
        this.lastMessage = lastMessage;
        this.typingStatus = typingStatus;
        this.isMsgRead = isMsgRead;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public DateTimeModel getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTimeModel dateTime) {
        this.dateTime = dateTime;
    }

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Boolean getTypingStatus() {
        return typingStatus;
    }

    public void setTypingStatus(Boolean typingStatus) {
        this.typingStatus = typingStatus;
    }

    public Boolean getMsgRead() {
        return isMsgRead;
    }

    public void setMsgRead(Boolean msgRead) {
        isMsgRead = msgRead;
    }
}
