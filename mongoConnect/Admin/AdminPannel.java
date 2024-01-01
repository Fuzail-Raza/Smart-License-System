package Admin;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DisplayInformation.DisplayDrivers;
import addSymbols.AddQuestion;
import addSymbols.UpdateSymbol;
import driverForm.UpdateDriverinfo;
import users.UpdateUser;
import users.Users;

public class AdminPannel {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JLabel heading;
    private JButton updateUserInfo;
    private JButton updateDriver;
    private JButton addUser;
    private JButton deleteUser;
    private JButton addSign;
    private JButton deleteSign;
    private JButton updateSign;
    private JButton display;
    private JButton displayDriver;

    public AdminPannel(){
        mainFrame=new JFrame();
        iniGUI();
        mainFrame.setSize(784, 449);
        mainFrame.add(mainPanel);

        mainFrame.setVisible(true);
    }

    private void iniGUI() {
        heading = new JLabel ("Admin Portal");
        updateUserInfo = new JButton ("Update User Info");
        updateDriver = new JButton ("Update Driver Data");
        addUser = new JButton ("Add User");
        deleteUser = new JButton ("Delete User");
        addSign = new JButton ("Add Sign Question");
        deleteSign = new JButton ("Delete Sign Question");
        updateSign = new JButton ("Update Sign Question");
        display = new JButton ("Display All Users");
        displayDriver = new JButton ("Display Driver Info");
        mainPanel=new JPanel();
        mainPanel.setLayout(null);

        Font headingFont = new Font("Arial", Font.BOLD, 28);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(headingFont);

        mainPanel.add (heading);
        mainPanel.add (updateUserInfo);
        mainPanel.add (updateDriver);
        mainPanel.add (addUser);
        mainPanel.add (deleteUser);
        mainPanel.add (addSign);
        mainPanel.add (deleteSign);
        mainPanel.add (updateSign);
        mainPanel.add (display);
        mainPanel.add (displayDriver);

        heading.setBounds (245, 80, 315, 70);
        updateUserInfo.setBounds (535, 185, 170, 30);
        updateDriver.setBounds (535, 285, 170, 30);
        addUser.setBounds (115, 185, 170, 30);
        deleteUser.setBounds (325, 185, 170, 30);
        addSign.setBounds (115, 235, 170, 30);
        deleteSign.setBounds (325, 235, 170, 30);
        updateSign.setBounds (535, 235, 170, 30);
        display.setBounds (115, 285, 170, 30);
        displayDriver.setBounds (325, 285, 170, 30);


        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);


        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Admin Portal");
        titledBorder.setTitleColor(Color.BLACK);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        titledBorder.setTitleFont(labelFont);

        addUser.addActionListener(buttonListner);
        deleteUser.addActionListener(buttonListner);
        updateUserInfo.addActionListener(buttonListner);
        addSign.addActionListener(buttonListner);
        deleteSign.addActionListener(buttonListner);
        updateSign.addActionListener(buttonListner);
        display.addActionListener(buttonListner);
        displayDriver.addActionListener(buttonListner);
        updateDriver.addActionListener(buttonListner);


        mainPanel.setBorder(titledBorder);

    }

    ActionListener buttonListner=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Add User")){
                addUserF();

            }
            else if(e.getActionCommand().equals("Delete User")){
                deleteUserF();

            }
            else if(e.getActionCommand().equals("Update User Info")){
                updateUserInfoF();
            }
            else if(e.getActionCommand().equals("Add Sign Question")){
                addSignF();

            }
            else if(e.getActionCommand().equals("Delete Sign Question")){
                deleteSignF();

            }
            else if(e.getActionCommand().equals("Update Sign Question")){
                updateSignF();

            }
            else if(e.getActionCommand().equals("Display All Users")){
                displayUsersF();
            }
            else if(e.getActionCommand().equals("Display Driver Info")){
                displayDriversF();

            }
            else if(e.getActionCommand().equals("Update Driver Data")){
                new UpdateDriverinfo(false);
            }
        }
    };

    private void displayDriversF() {
        new DisplayDrivers(true);
    }

    private void displayUsersF() {
        new DisplayDrivers(false);
    }

    private void updateSignF() {
        new UpdateSymbol(false);
    }

    private void deleteSignF() {
        new UpdateSymbol(true);
    }

    private void addSignF() {
        new AddQuestion();
    }

    private void updateUserInfoF() {
        new UpdateUser(false);
    }

    private void deleteUserF() {
        new UpdateUser(true);
    }

    private void addUserF() {
        new Users();
    }

    public static void main(String[] args) {
        new AdminPannel();
    }
}
