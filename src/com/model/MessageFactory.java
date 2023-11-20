package com.model;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MessageFactory {

    public String pathText;
    public File chatFile;
    public Scanner chatScanner;
    public List<Message> myArray = new ArrayList<>();
       public MessageFactory(String location){ // DEFAULT CONSTRUCTOR
            setChatFile(location);
            createFile();
            openFile();
           // printChatArray();
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
                    myArray.add(message);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        //FUNCTION TO SPLIT THE ARRAYLIST OF MESSAGES TO A QUESTION LIST AND AN ANSWER LIST
        public void splitList(){
           for(Message m : myArray){
               if(m.getSender().equalsIgnoreCase(K.tutor)){
                       K.questionList.add(m);
               }else{
                   K.senderList.put(m.getSender(),0.0);
                   K.answerList.add(m);
               }
           }
        }
        //PRINTS THE ARRAY OF MESSAGES TO THE CONSOLE *** FOR TESTING ****
        public void printChatArray(){
           for(Message m : myArray){
               System.out.println(m.toString());
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
        //ANALYZE FUNCTION TO ALLOCATE GRADES BASED ON PARTICIPATION
        public void analyze(){
           List<String> allocatedList = new ArrayList<>();
           int i = 1;
           K.messageSize = K.questionList.size();

           if(K.answerList.peek().getMessageDate().isLessThan(K.questionList.get(0).getMessageDate())){
               System.out.println("Less than first date");
               while(K.answerList.peek().getMessageDate().isLessThan(K.questionList.get(0).getMessageDate())){
                   K.answerList.poll();

               }
           }
           for(Message question : K.questionList){
               System.out.println("Question : "+ question.getMessageBody());
               if(i < K.questionList.size()-1){
                   while (K.answerList.peek().getMessageDate().isLessThan(K.questionList.get(i).getMessageDate())){
                       Message currentAnswer = K.answerList.poll();
                       System.out.println(currentAnswer.getMessageBody());
                       if(isMessageLogical(currentAnswer.getMessageBody())){
                           Double allocatedGrade = K.senderList.get(currentAnswer.getSender())+100;
                           if (!allocatedList.contains(currentAnswer.getSender())){
                               K.senderList.put(currentAnswer.getSender(),allocatedGrade);
                               allocatedList.add(currentAnswer.getSender());
                           }else{
                               System.out.println(currentAnswer.getSender() + "Already in List");
                           }
                       }else{
                           System.out.println("Illogical answer");
                       }
                   }
               }else{
                   int x =0;
                   while(x <= K.answerList.size()){
                       Message thisAnswer = K.answerList.poll();
                       System.out.println(thisAnswer.getMessageBody());
                       if(isMessageLogical(thisAnswer.getMessageBody())){
                           Double allocatedGrade = K.senderList.get(thisAnswer.getSender())+100;
                           if(!allocatedList.contains(thisAnswer.getSender())){
                               K.senderList.put(thisAnswer.getSender(),allocatedGrade);
                               allocatedList.add(thisAnswer.getSender());
                           }else{
                               System.out.println("Already in List");
                           }
                       }else{
                           System.out.println("Illogical answer");
                       }
                       x++;
                   }
               }

               i++;
               allocatedList.clear();
           }

        }
        //FUNCTION TO CALCULATE THE AVERAGE GRADE BASED ON THE NUMBER OF QUESTIONS
        public void getAverageGrade(JTextArea ta){
           ta.setText("Participation Analysis \n \n");
           for(String key : K.senderList.keySet()){
               Double averageScore = K.senderList.get(key) / K.messageSize;
               K.senderList.put(key,averageScore );
               ta.append(key+" : "+ String.format("%.2f",K.senderList.get(key))+"%\n\n");
                       //K.senderList.get(key)+"%\n");
               System.out.println(key +" : "+K.senderList.get(key));
           }
       }
           //FUNCTION THAT CHECKS IF AN ANSWER IS LOGICAL BY COMPARING IT WITH THE MOST COMMONLY USED ENGLISH WORDS
           public boolean isMessageLogical(String message){
           int i = 0;
           boolean flag = false;

           while(i<98 && !flag){
               if(message.toUpperCase().contains(K.wordList[i].toUpperCase())){
                   flag = true;
               }else{
                   flag = false;
               }
               i++;
           }
            return flag;
           }

           public void upToSpeed(){
            while(K.answerList.peek().getMessageDate().isLessThan(K.questionList.get(0).getMessageDate())){
                K.answerList.poll();
            }
           }

}
