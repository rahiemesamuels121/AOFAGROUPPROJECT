package com.view;

import com.controller.MyViewController;
import com.model.K;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyView implements ActionListener{

    //VIEW ELEMENTS INCLUDED IN THE GUI
    public JButton btnChooseFile;
    public JButton btnChooseLecturer;
    public JButton btnAnalyze;
    public JButton btnQList;
    public JButton btnClear;
    public JLabel tutorInfo;
    public JButton btnAList;
    public JTextArea textArea ;
    public JLabel textField;
    public JScrollPane scrollPane;
    public JMenuBar menuBar;
    public JMenu menu;
    public JMenuItem menuItem;
    MyViewController vc = new MyViewController();

    public MyView(){
        configureFrame();
    }

    public void configureFrame(){
//INSTANTIATES EACH VIEW ELEMENT
      JFrame frame = new JFrame("My Frame");
        btnChooseLecturer = new JButton("Choose Lecturer");
        tutorInfo = new JLabel();
        btnChooseFile = new JButton("Choose Participant List");
        btnQList = new JButton("Question List");
        btnAList = new JButton("Answers List");
        btnClear = new JButton("CLEAR");
        btnAnalyze = new JButton("Analyze");
        textArea = new JTextArea("NO TEXT");
        textField = new JLabel("                                                       Message Information");
        menuBar = new JMenuBar();
        menu = new JMenu("Select Tutor");
        menuItem = new JMenuItem("Input Tutor");
        tutorInfo.setText("Tutor is :" + K.tutor);

//CONFIGURATION AND ACTION LISTENER FOR THE CHOSE FILE BUTTON
      btnChooseFile.setBounds(5,100,200,50);
      btnChooseFile.setVisible(false);
      btnChooseFile.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              btnChooseFile.setName("Pressed");
           vc.chooseFile();
           vc.viewAnswersList(textArea);
          }
      });
//CONFIGURATION AND ACTION LISTENER FOR THE QUESTION LIST BUTTON
        btnQList.setBounds(5,160,200,50);
        btnQList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                vc.viewQuestionList(textArea);
            }
        });
//CONFIGURATION AND ACTION LISTENER FOR ANSWER LIST BUTTON
        btnAList.setBounds(5,220,200,50);
        btnAList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                vc.viewAnswersList(textArea);
            }
        });
 //CONFIGURATION AND ACTION LISTENER FOR CLEAR BUTTON
        btnClear.setBounds(5,280,200,50);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.clearButtonPressed(textArea,btnChooseFile);
            }
        });

        btnAnalyze.setBounds(5,340,200,50);
        btnAnalyze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.chatFactory.analyze();
            }
        });
//CONFIGURATION FOR THE TEXT AREA THAT WILL BE USED AS THE DISPLAY
      textArea.setBounds(250,100,500,500);
      textArea.setEditable(false);
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      textArea.setFont(textArea.getFont().deriveFont(20f));
//CONFIGURATION FOR THE TEXT AREA
      textField.setBounds(250,50,500,50);
      //textField.setEditable(false);
      textField.setBackground(Color.CYAN);
      menuItem.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

              String tutorName = JOptionPane.showInputDialog(menuItem,"Input Lecturer Name \nLeave blank for default");
              vc.setTutorName(tutorName);
              tutorInfo.setText("Tutor is :" + K.tutor);
              btnChooseFile.setVisible(true);



          }
      });
      menu.add(menuItem);
      menuBar.add(menu);

        tutorInfo.setBounds(0,50,700,50);


//CONFIGURATION AND ADDING EACH ELEMENT TO  THE FRAME
      frame.setLayout(null);
      frame.setSize(800,800);
      frame.setResizable(false);
      frame.add(btnChooseFile);
      frame.add(textArea);
      frame.add(textField);
      frame.add(btnQList);
      frame.add(btnAList);
      frame.add(btnClear);
      frame.add(btnAnalyze);
      frame.add(tutorInfo);
      frame.setVisible(true);
      frame.setBackground(Color.BLUE);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setJMenuBar(menuBar);
    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
