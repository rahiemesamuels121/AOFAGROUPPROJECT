package com.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatFactory {

    public String pathText;
    public File chatFile;
    public Scanner chatScanner;
    public List<Message> myarray = new ArrayList<>();
    String mystring ;
       public  ChatFactory(String location){ // DEFAULT CONSTRUCTOR
            setChatFile(location);
            createFile();
            openFile();
            printChatArray();
            splitList();
        }
        //FUNCTION TO OPEN FILE USING A SCANNER OBJECT
        public void openFile() {
            try {
                chatScanner = new Scanner(chatFile);
                while(chatScanner.hasNext()){
                    Message message = new Message();
                    String[] sender ;
                    String messageBody;
                    String infoLine ;
                    String[] timeStamp;
                    infoLine = chatScanner.nextLine();
                    timeStamp = infoLine.split(":");
                    sender = infoLine.split("To");
                    sender = sender[0].split("From");
                    messageBody = chatScanner.nextLine();
                    messageBody = messageBody.trim();
                    message.setSender(sender[1].trim());
                    message.setMessageBody(messageBody);
                    message.setMessageDate(timeStamp[0],timeStamp[1],timeStamp[2].substring(0,2));
                    myarray.add(message);
                    // K.senderList.put(message.getSender(),0.0);
                   // System.out.println(K.senderList.get(sender[1].trim()));
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        //FUNCTION TO SPLIT THE ARRAYLIST OF MESSAGES TO A QUESTION LIST AND AN ANSWER LIST
        public void splitList(){
           Integer arrayCounter = 0;
           while(arrayCounter < myarray.size()){
               if(myarray.get(arrayCounter).getSender().equals(K.tutor)){
                  // if(myarray.get(arrayCounter).getMessageBody().contains("?")){
                       K.QuestionList.add(myarray.get(arrayCounter));
                  // }
               }else{
                   K.AnswerList.add(myarray.get(arrayCounter));
               }
               arrayCounter++;
           }
        }
        //PRINTS THE ARRAY OF MESSAGES TO THE CONSOLE *** FOR TESTING ****
        public void printChatArray(){
           Integer arrayCounter = 0 ;
           while(arrayCounter <= myarray.size()-1 ){
               System.out.println(myarray.get(arrayCounter).toString());
               //System.out.println("Array now has" + arrayCounter + "elements ");
               arrayCounter++;
           }
        }
        //SETTER FUNCTION FOR THE FILEPATH THAT WAS SELECTED
        public void setChatFile(String filePath){
           this.pathText = filePath;
        }
        //CREATES A NEW FILE INSTANCE USING THE PATH OF THE SELECTED FILE
        public void createFile (){
            chatFile = new File(pathText);
        }
        public void analyze(){
            System.out.println("In Analyze function");
           K.messageSize = K.QuestionList.size();
           Integer i = 0;
            while(i<K.QuestionList.size()-1){
                System.out.println("In Loop Iteration " + i);
                Integer k = 0;
                    while(k<K.AnswerList.size()){
                        if(K.AnswerList.get(k).getMessageDate().isGreaterThan(K.QuestionList.get(i).getMessageDate())){
                            K.senderList.put(K.AnswerList.get(k).getSender(),100.0);
                        }
                        k++;
                    }
               i++;
            }
            System.out.println(K.senderList);
        }
}
