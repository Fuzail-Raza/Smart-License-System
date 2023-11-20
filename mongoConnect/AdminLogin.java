import javax.swing.*;
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

        innerPanel=new JPanel(new GridLayout(4,2));

        heading=new JLabel("User LogIN");
        innerPanel.add(heading);
        innerPanel.add(new JLabel());

        userNameLabel=new JLabel("User Name : ");
        innerPanel.add(userNameLabel);

        userName=new JTextField();
        innerPanel.add(userName);

        passwordLabel=new JLabel("Password");
        innerPanel.add(passwordLabel);

        password=new JPasswordField();
        innerPanel.add(password);

        LogIN=new JButton("LOGIN");
        innerPanel.add(LogIN);

        mainFrame.add(innerPanel);
        mainFrame.setVisible(true);
        mainFrame.setSize(720,600);
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
