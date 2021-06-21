package com.example.chatterbox.objects;

public class MessageModel {

    private String message;
    private Boolean isMsgFromYou;
    private String status;

    public MessageModel(){}

    public MessageModel(String message, Boolean isMsgFromYou, String status) {
        this.message = message;
        this.isMsgFromYou = isMsgFromYou;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsMsgFromYou() {
        return isMsgFromYou;
    }

    public void setIsMsgFromYou(Boolean isMsgFromYou) {
        this.isMsgFromYou = isMsgFromYou;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
