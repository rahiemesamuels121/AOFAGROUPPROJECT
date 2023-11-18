package com.view;

import com.controller.MyViewController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyView implements ActionListener{
    public JButton btnChooseFile;
    public JButton btnChooseLecturer;
    public JButton btnQList;
    public JButton btnAList;
    public JTextArea textArea ;
    public JTextField textField;
    public JScrollPane scrollPane;
    public JMenuBar menuBar;
    public JMenu menu;
    public JMenuItem menuItem;
    MyViewController vc = new MyViewController();

    public MyView(){
        configureFrame();
    }

    public void configureFrame(){

      JFrame frame = new JFrame("My Frame");
        btnChooseLecturer = new JButton("Choose Lecturer");
        btnChooseFile = new JButton("Choose Participant List");
        textArea = new JTextArea();
        textField = new JTextField("                                                               Message Information");
        menuBar = new JMenuBar();
        menu = new JMenu("Select Tutor");
        menuItem = new JMenuItem("Select Tutor");

     // btnChooseFile.setSize(100,50);
      btnChooseFile.setBounds(5,100,200,50);
      btnChooseFile.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              btnChooseFile.setName("Pressed");
           vc.chooseFile();
           vc.updateTextArea(textArea);
          }
      });


      textArea.setBounds(250,100,500,500);
      textArea.setEditable(false);
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      textArea.setFont(textArea.getFont().deriveFont(20f));


      textField.setBounds(250,50,500,50);
      textField.setEditable(false);
      textField.setBackground(Color.CYAN);

      menu.add(menuItem);
      menuBar.add(menu);



      frame.setLayout(null);
      frame.setSize(800,800);
      frame.setResizable(false);
      frame.add(btnChooseFile);
      frame.add(textArea);
      frame.add(textField);
      frame.setVisible(true);
      frame.setBackground(Color.BLUE);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setJMenuBar(menuBar);
    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
