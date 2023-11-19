package com.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MessageFactory {

    public String pathText;
    public File chatFile;
    public Scanner chatScanner;
    public List<Message> myarray = new ArrayList<>();
    String mystring ;
       public MessageFactory(String location){ // DEFAULT CONSTRUCTOR
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
           for(Message m :  myarray){
               if(m.getSender().equals(K.tutor)){
                       K.questionList.add(m);
               }else{
                   K.senderList.put(m.getSender(),0.0);
                   K.answerList.add(m);
               }
           }
        }
        //PRINTS THE ARRAY OF MESSAGES TO THE CONSOLE *** FOR TESTING ****
        public void printChatArray(){
           int arrayCounter = 0 ;
           for(Message m : myarray ){
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
           K.messageSize = K.questionList.size();
           int i = 1;

            for(Message questions : K.questionList){
                System.out.println("\nQuestion :"+questions.getMessageBody());
                  //  for (Message answers : K.answerList){
                        if(i < K.questionList.size()-1){
                            while (K.answerList.peek().getMessageDate().isLessThan(K.questionList.get(i).getMessageDate())) {
                                Message currentAnswer = K.answerList.poll();
                                System.out.println("Answer " + ":" + currentAnswer.getMessageBody());

                                if(isMessageLogical(currentAnswer.getMessageBody())){
                                    Double currentScore = K.senderList.get(currentAnswer.getSender())+100;
                                    System.out.println(currentScore);
                                    K.senderList.put(currentAnswer.getSender(),currentScore);
                                }else{
                                    System.out.println("Illogical answer");
                                }

                            }
                        }else{
                            for(Message mess : K.answerList){
                                Message currentAnswer = K.answerList.poll();
                                System.out.println("Answer " + ":" + currentAnswer.getMessageBody());

                                if(isMessageLogical(mess.getMessageBody())){
                                    Double currentScore = K.senderList.get(currentAnswer.getSender())+100;
                                    System.out.println(currentScore);
                                    K.senderList.put(currentAnswer.getSender(), currentScore);
                                }else{
                                    System.out.println("Message is Illogical");
                                }

                            }
                        }
                i++;
            }
           // System.out.println(K.senderList);
            getAverageGrade();
        }

        //FUNCTION TO CALCULATE THE AVERAGE GRADE BASED ON THE NUMBER OF QUESTIONS
        public void getAverageGrade(){
           for(String key : K.senderList.keySet()){
               Double averageScore = K.senderList.get(key) / K.messageSize;
               K.senderList.put(key,averageScore );
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

}
