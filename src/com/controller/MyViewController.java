package com.controller;

import com.model.ChatFactory;
import com.model.K;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.ArrayList;

public class MyViewController {
    public ChatFactory chatFactory;
    public void setPath( String path ){
        Integer x = 0;
        chatFactory = new ChatFactory(path);
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
        if (!K.AnswerList.isEmpty() ){
            ta.setText("");
            Integer x =0 ;
            while (x< K.AnswerList.size()){
                ta.append(K.AnswerList.get(x).toString());
                x++;
            }
        }else{
            ta.setText("Select a Tutor to continue");
        }

    }
    //PRINTS ALL THE MESSAGE OBJECTS THAT ARE STORED IN THE STATIC ARRAYLIST
public void viewQuestionList(JTextArea ta){
    if (!K.QuestionList.isEmpty() ){
        System.out.println("Viewing question list");
        ta.setText("");
        Integer qCounter = 0 ;
        while (qCounter < K.QuestionList.size()){
            ta.append(K.QuestionList.get(qCounter).toString());
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
    K.QuestionList = new ArrayList<>();
    K.AnswerList = new ArrayList<>();
    chatFactory.myarray = null;
    chatFactory.chatFile = null;
    chatFactory.pathText = null;
    ta.setText("");
    b.setVisible(false);

}

}
