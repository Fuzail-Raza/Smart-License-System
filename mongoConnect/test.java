import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mongoPackage.mongoConnect;
import org.bson.Document;
import org.bson.types.Binary;

import java.util.HashMap;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;
public  class test{

    private JPanel innerPanel;
    private JLabel heading;
    private JLabel userNameLabel;
    private JTextField userName;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton LogIN;

    public test(){

        initGUI();

        addListner();
    }

    void initGUI(){

        JFrame mainFrame = new JFrame();

        innerPanel=new JPanel(null);

        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);


        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Driving License User LOGIN");
        titledBorder.setTitleColor(Color.BLACK);
        Font labelFont = new Font("Arial", Font.BOLD, 28);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleFont(labelFont);

        innerPanel.setBorder(titledBorder);

        heading=new JLabel("User LOGIN");
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setFont(labelFont);
        innerPanel.add(heading);

        innerPanel.add(new JLabel());

        Font font = new Font("Arial", Font.BOLD, 16);
        userNameLabel=new JLabel("User Name : ");
        userNameLabel.setFont(font);
        innerPanel.add(userNameLabel);

        userName=new JTextField();
        innerPanel.add(userName);

        passwordLabel=new JLabel("Password");
        passwordLabel.setFont(font);
        innerPanel.add(passwordLabel);

        password=new JPasswordField();
        innerPanel.add(password);

        LogIN=new JButton("LOGIN");
        LogIN.setFont(font);

        innerPanel.add(LogIN);

        LogIN.setBounds (270, 285, 170, 30);
        userNameLabel.setBounds (190, 150, 130, 30);
        passwordLabel.setBounds (190, 200, 130, 30);
        password.setBounds (335, 200, 170, 30);
        userName.setBounds (335, 150, 170, 30);
        heading.setBounds (245, 80, 205, 40);

        mainFrame.add(innerPanel);
        mainFrame.setVisible(true);
        mainFrame.setSize(720,580);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void addListner(){

        LogIN.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String hashedPassword = BCrypt.hashpw("123", BCrypt.gensalt());
                JOptionPane.showMessageDialog(null,hashedPassword);

                if (BCrypt.checkpw(String.valueOf(password.getPassword()), hashedPassword)) {
                    System.out.println("Password Matched!");
                } else {
                    System.out.println("Invalid Password!");
                }

            }

        });

    }

    public static void main(String[] args) {
            test user=new test();

    }

}


