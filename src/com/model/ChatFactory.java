package com.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatFactory {

    String pathText;
    File chatFile;
    Scanner chatScanner;
    public List<Message> myarray = new ArrayList<>();
    String mystring ;


       public  ChatFactory(String location){ // DEFAULT CONSTRUCTOR
            setChatFile(location);
            createFile();
            openFile();
            printChatArray();
        }

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
                    message.setMessageDate("0","0","58");
                    myarray.add(message);

                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public void splitList(){
           Integer arrayCounter = 0;
           while(arrayCounter < myarray.size()){
               System.out.println("Start splitting list");
               if(myarray.get(arrayCounter).getSender().equals(K.lecturer)){
                   if(myarray.get(arrayCounter).getMessageBody().contains("?")){
                       K.QuestionList.add(myarray.get(arrayCounter));
                   }
               }else{
                   K.AnswerList.add(myarray.get(arrayCounter));
               }
               arrayCounter++;
           }
        }

        public void printChatArray(){
           Integer arrayCounter = 0 ;
           while(arrayCounter <= myarray.size()-1 ){
               System.out.println(myarray.get(arrayCounter).toString());
               //System.out.println("Array now has" + arrayCounter + "elements ");

               arrayCounter++;
           }
        }

        public void setChatFile(String filePath){
           this.pathText = filePath;
        }

        public void createFile (){
            chatFile = new File(pathText);
        }




}
