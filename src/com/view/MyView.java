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
    public JButton btnChooseTutor;
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
    JPanel panLeft , panRight;
    JLabel detailLabel;
    MyViewController vc = new MyViewController();
    public MyView(){
        configureFrame();
    }
    public void configureFrame(){
//INSTANTIATES EACH VIEW ELEMENT
      JFrame frame = new JFrame("Zoom Chat Analyzer");
      detailLabel = new JLabel();
      panLeft= new JPanel();
      panRight=new JPanel();
      btnChooseTutor = new JButton("Choose Tutor");
      tutorInfo = new JLabel();
      btnChooseFile = new JButton("Choose Participant List");
      btnQList = new JButton("Question List");
      btnAList = new JButton("Answers List");
      btnClear = new JButton("CLEAR");
      btnAnalyze = new JButton("Analyze");
      textArea = new JTextArea("NO TEXT");
      textField = new JLabel("Message Information");
      menuBar = new JMenuBar();
      menu = new JMenu("Select Tutor");
      menuItem = new JMenuItem("Input Tutor");
      tutorInfo.setText("Tutor is :" + K.tutor);
      scrollPane=new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//CONFIGURE LEFT PANEL
        panLeft.add(btnChooseFile);
        panLeft.add(btnQList);
        panLeft.add(btnAList);
        panLeft.add(btnAnalyze);
        panLeft.add(btnClear);
        panLeft.add(btnChooseTutor);
        panLeft.setBackground(Color.lightGray);
        panLeft.setPreferredSize(new Dimension(200, 700));
//CONFIGURE RIGHT PANEL
        panRight.add(textField);
        panRight.add(scrollPane);
        panRight.add(tutorInfo);
        panRight.add(detailLabel);
        panRight.setPreferredSize(new Dimension(500, 700));
        panRight.setBackground(Color.LIGHT_GRAY);
//CONFIGURATION AND ACTION LISTENER FOR THE CHOOSE FILE BUTTON
      btnChooseFile.setPreferredSize(new Dimension(200, 50));
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
        btnQList.setPreferredSize(new Dimension(200, 50));
        btnQList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                vc.viewQuestionList(textArea);
            }
        });
//CONFIGURATION AND ACTION LISTENER FOR ANSWER LIST BUTTON
        btnAList.setPreferredSize(new Dimension(200, 50));
        btnAList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                vc.viewAnswersList(textArea);
            }
        });
 //CONFIGURATION AND ACTION LISTENER FOR CLEAR BUTTON
        btnClear.setPreferredSize(new Dimension(200, 50));
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.clearButtonPressed(textArea,btnChooseFile);
            }
        });
        btnAnalyze.setPreferredSize(new Dimension(200, 50));
        btnAnalyze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!K.answerList.isEmpty()){
                    detailLabel.setText("*************** "+K.questionList.size() + " Questions ||"+K.answerList.size()+" Responses ||" +K.senderList.size()+" Participants ***************");
                    vc.messageFactory.analyze();
                    vc.messageFactory.getAverageGrade(textArea);
                }else{
                    textArea.setText("select a tutor\nthen a file\nthen analyze");
                }
            }
        });
//CONFIGURATION FOR THE TEXT AREA THAT WILL BE USED AS THE DISPLAY
      scrollPane.setPreferredSize(new Dimension(400, 600));
      textArea.setEditable(false);
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      textArea.setFont(textArea.getFont().deriveFont(16f));
//CONFIGURATION FOR THE TEXT AREA
      //textField.setEditable(false);
      //textField.setBackground(Color.CYAN);
        //menuItem.setBounds(0,0,10,20);

//CONFIGURATION AND ACTION LISTENER FOR CHOSE LECTURER BUTTON
        btnChooseTutor.setPreferredSize(new Dimension(200, 50));
      btnChooseTutor.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

              String tutorName = JOptionPane.showInputDialog(menuItem,"Input Lecturer Name \nLeave blank for default");
              vc.setTutorName(tutorName);
              tutorInfo.setText("**********Tutor is :" + K.tutor+"**********");
              btnChooseFile.setVisible(true);
          }
      });
        tutorInfo.setBounds(0,50,700,50);
//CONFIGURATION AND ADDING EACH ELEMENT TO  THE FRAME
      frame.setLayout(new FlowLayout());
      frame.setSize(800,800);
      frame.setResizable(false);
      frame.add(panLeft,Component.CENTER_ALIGNMENT);
      frame.add(panRight);
      frame.setVisible(true);
      frame.setBackground(Color.black);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //frame.setJMenuBar(menuBar);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
