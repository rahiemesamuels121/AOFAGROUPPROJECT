package com.controller;

import com.model.MessageFactory;
import com.model.K;
import com.model.Message;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyViewController {
    public MessageFactory messageFactory;
    public void setPath( String path ){
        Integer x = 0;
        messageFactory = new MessageFactory(path);
    }
    //FUNCTION TO SHOW A FILE CHOOSER DIALOGUE IN ORDER TO SELECT A FILE
    public void chooseFile(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text File", "txt", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        setPath(chooser.getSelectedFile().getAbsolutePath());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        }
    }
    //PRINTS ALL THE MESSAGE OBJECTS THAT ARE STORED IN THE STATIC ARRAYLIST
    public void viewAnswersList(JTextArea ta){
        if (!K.answerList.isEmpty() ){
            ta.setText("");
           // Integer x =0 ;
            for  (Message answerMessage : K.answerList){
                ta.append(answerMessage.toString());
               // x++;
            }
        }else{
            ta.setText("Select a Tutor to continue");
        }

    }
    //PRINTS ALL THE MESSAGE OBJECTS THAT ARE STORED IN THE STATIC ARRAYLIST
public void viewQuestionList(JTextArea ta){
    if (!K.questionList.isEmpty() ){
        System.out.println("Viewing question list");
        ta.setText("");
        Integer qCounter = 0 ;
        while (qCounter < K.questionList.size()){
            ta.append(K.questionList.get(qCounter).toString());
            qCounter++;
        }
    }else{
        ta.setText("Select a Tutor to continue");
    }
}
public void setTutorName(String tutorName){
    if (!tutorName.equals("")){
        K.tutor = tutorName;
    }
}
public void clearButtonPressed(JTextArea ta,JButton b ){
    K.questionList.clear();
    K.answerList.clear();
    K.senderList.clear();
    messageFactory.myArray.clear();
    messageFactory.chatFile = null;
    messageFactory.pathText = null;
    ta.setText("");
    b.setVisible(false);

}

}
