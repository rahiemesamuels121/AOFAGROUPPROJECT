package com.controller;

import com.model.ChatFactory;
import com.model.K;
import com.view.MyView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class MyViewController {
    public ChatFactory chatFactory;
    public void setPath( String path ){
        Integer x = 0;
        chatFactory = new ChatFactory(path);
    }
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
    public void updateTextArea(JTextArea ta){

        chatFactory.splitList();
        Integer x =0 ;
        while (x< K.AnswerList.size()){
            ta.append(K.AnswerList.get(x).toString());
            x++;
        }

    }



}
