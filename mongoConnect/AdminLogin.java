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

                String user="Fuzail",pswd="123";
                String enteredPassword = String.valueOf( password.getPassword());

                if(userName.getText().equals(user) && enteredPassword.equals(pswd)){

                        JOptionPane.showMessageDialog(null, "User Login SuccessFully");
//                        mainFrame.dispose();
                    try {
                        String path="E:\\Programms\\Java\\ACP-Tasks\\JAVA project\\Images\\1675105387954.jpeg";
                        mongoConnect d1 = new mongoConnect();

                        Map<String, Object> documentMap = new HashMap<>();

                        documentMap.put("Ide", 39096);
                        documentMap.put("name",userName.getText());
                        documentMap.put("Password", String.valueOf(password.getPassword()));
                        documentMap.put("city", "Lahore");
                        documentMap.put("Image", d1.storeImage(path));
//                        d1.createDocument(documentMap);
//                        d1.createDocument(39091, userName.getText(), String.valueOf(password.getPassword()), "Lahore",path);
//                            Document d=d1.readDocument(39096);
//                            JLabel imageLabel=d1.fetchImage(d.get("Image",Binary.class));
//                            JFrame f1=new JFrame();
//                            f1.setVisible(true);
//                            f1.add(imageLabel);
//                        d1.deleteDocument(39096);
//                          d1.updateDocument(39091,"Fuzail Raza");



//                        String jsonString = "Id :" D , \"name\": \"Fuzail\", \"Password\": \"123\", \"city\": \"Lahore\", \"_id\": {\"$oid\": \"6550d440ee9fd155e82a0b4f\"}}";

//                        JOptionPane.showMessageDialog(null,jsonString);

                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex.getMessage()+ "insertion");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "User Login Failed");
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
