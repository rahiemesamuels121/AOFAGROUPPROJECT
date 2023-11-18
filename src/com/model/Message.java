package com.model;

public class Message {

    private String sender;
    private String messageBody;
    private Date messageDate;



//PRIMARY CONSTRUCTOR FOR THE MESSAGE MODEL
    public Message(Integer timeStamp , String sender, String receiver, String messageBody){
        //this.timeStamp = timeStamp;
        this.sender = sender;
        this.messageBody = messageBody;
    }

    public Message() {
        this.sender = "";
        this.messageBody = "";
    }
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
    @Override
    public String toString() {
        return " \n Sender :" + sender+" \n Message_Body: "+messageBody + '\n'+" Time_Stamp " + messageDate.toString() ;
    }


    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String hour, String minute , String seconds) {
        messageDate = new Date(hour,minute,seconds);

    }
}
