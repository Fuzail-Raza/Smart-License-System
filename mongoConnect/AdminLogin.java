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
class Login{

    private JPanel innerPanel;
    private JLabel heading;
    private JLabel userNameLabel;
    private JTextField userName;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton LogIN;

    public Login(){

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

                try {
                    mongoConnect adminFetch = new mongoConnect("Driving_Center", "AdminInfo");
//                    Map<String, Object> adminDataSave = new HashMap<>() ;
//                    adminDataSave.put("Name","Fuzail");
//                    adminDataSave.put("Password","123");
//                    adminFetch.createDocument(adminDataSave);
                    Document admin = adminFetch.readDocument("Name",userName.getText().trim());
                    if (admin == null) {
                        JOptionPane.showMessageDialog(innerPanel, "User Not Found");
                    }
                    else {
                        String enteredPassword = String.valueOf(password.getPassword());
                        if (enteredPassword.equals(admin.getString("Password"))){
                            JOptionPane.showMessageDialog(innerPanel, "LogIN Successfully");

                        }

                        else {
                            JOptionPane.showMessageDialog(innerPanel, "User Not Found");
                        }
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(innerPanel, "Failed to LOGIN");
                }
            }

        });

    }

}




public class AdminLogin {
    public static void main(String[] args) {

        Login user=new Login();

    }
}
