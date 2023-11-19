package com.model;

import java.util.ArrayList;
import java.util.List;

public class MyQueue {
    List<Message> messageQueue = new ArrayList<>();
    int front;
    int rear;
    int size;




    public Message deQueue(){
        Message message = new Message();
        message = messageQueue.get(size-1);
        messageQueue.remove(front);
        this.front++;

        return message;
    }
    public void printQueue(){
        int i = this.size;
        if(size == 0){
            System.out.println(deQueue());
        }else{
            this.size--;
            System.out.println(i);
            this.printQueue();
        }
    }


    public int getSize() {
        return messageQueue.size();
    }

    public void setSize(int size) {
        this.size = size;
    }
}
