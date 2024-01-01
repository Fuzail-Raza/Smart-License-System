package users;

import com.infobip.sms.SendSMS;
import com.toedter.calendar.JDateChooser;
import mongoPackage.mongoConnect;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Users {
    private JFrame mainFrame;
    private JPanel User_Form;
    private JDateChooser dateOfBirthInput;
    private JTextField phoneNoInput;
    private JLabel cnic;
    private JLabel fatherName;
    private JLabel fatherCnic;
    private JTextField fatherCnicInput;
    private JTextField cnicInput;
    private JTextField nameInput;
    private JTextField fatherNameInput;
    private JLabel dateofBirth;
    private JLabel name;
    private JLabel age;
    private JLabel ageLabel;
    private JLabel phoneNo;
    private JLabel bloodGroup;
    JComboBox<String> bloodGroupsList;
    private JButton submitButton;
    private JLabel picture;
    private JDateChooser dateOfJoining;
    private JSeparator separator1;
    private JLabel dateOfIssue1;
    private JLabel dateOfIssue1Label;
    private JLabel dateOfExpiry1Label;
    private JComboBox<String> type1List;
    private JLabel password;
    private JLabel rePassword;
    private JPasswordField passwordText;
    private JPasswordField rePasswordText;
    private JLabel userID;
    private JLabel userIDText;
    int xalignD =0, yalignD =0;
    int xalignL=0, yalignL =0;
    mongoConnect conncetionUsers;
    mongoConnect conncetionids;

    public Users(){

        initGUI();

    }

    void createConnection(){
        conncetionUsers=new mongoConnect("Driving_Center","usersInfo");
        conncetionids=new mongoConnect("Driving_Center","id_Collection");
    }

    private void initGUI(){
        createConnection();

        mainFrame=new JFrame();
        mainFrame.setTitle("Driver Info");

        xalignL=-20;
        xalignD=-20;
        yalignL=-20;
        yalignD=-50;

        User_Form =new JPanel();
        User_Form.setLayout(null);

        addDriverInfo();

        separator1=new JSeparator();
        separator1.setBounds (50+ xalignD, 330+yalignD, 725, 20);
        User_Form.add(separator1);


        submitButton=new JButton("Submit Now");
        submitButton.setBounds (165+ xalignL, 515+ yalignL, 540, 35);
        User_Form.add(submitButton);

        mainFrame.add(User_Form);
        mainFrame.setVisible(true);
        mainFrame.setSize(899, 592);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dateOfBirthInput.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                handleSelectedDate();
            }

        });
        dateOfJoining.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                Date selectedDate = dateOfJoining.getDate();
                if (selectedDate != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date currentDate = new Date();

                    if (selectedDate.after(currentDate)) {
                        JOptionPane.showMessageDialog(mainFrame,"Cannot Select Upcoming Date","Wrong Date",JOptionPane.ERROR_MESSAGE);
                        dateOfJoining.setDate(currentDate);
                    }
                }

            }

        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (isFormValid()) {
                saveData();
                    String message = "Dear " + nameInput.getText() + ",\nRegistration Confirmed .\nYour User ID is :"+userIDText.getText()+"\nYour Password is :"+ Arrays.toString(passwordText.getPassword()) +".";
//                    SendSMS.send(message);
                }
            }
        });

    }


    private boolean handleSelectedDate() {
        Date currentDate = new Date();

        if (Integer.parseInt(calculateAge(selectedDate(true)))<18) {
            // The selected date is in the future
            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Age must be Greater than 18.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE);
            return false;
//            dateOfBirthInput.setDate(currentDate);
        }
        else {
            ageLabel.setText(calculateAge(selectedDate(true)));
            return true;
        }

    }

    private boolean isFormValid() {

        if (nameInput.getText().trim().isEmpty() || cnicInput.getText().trim().isEmpty() ||
                fatherNameInput.getText().trim().isEmpty() || phoneNoInput.getText().trim().isEmpty() ||
                dateOfBirthInput.getDate() == null) {
            JOptionPane.showMessageDialog(mainFrame, "Please fill in all the required fields.", "Form Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String cnicPattern = "^[0-9]{13}$";
        if (!cnicInput.getText().matches(cnicPattern)) {
            JOptionPane.showMessageDialog(mainFrame, "CNIC must be 13 digits long.", "Form Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        String phonePattern = "^(030[1-9]|031[0-9]|032[0-9]|033[0-5])[0-9]{7}$";
        if (!phoneNoInput.getText().matches(phonePattern)) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid phone number. Please use the format: 03XXXXXXXXX.", "Form Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!handleSelectedDate()){
            return false;
        }

        if(!Arrays.equals(passwordText.getPassword(),rePasswordText.getPassword())){
            JOptionPane.showMessageDialog(mainFrame, "Enter Password and Re-Enter Password are not Same", "Form Validation Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        return true;
    }

    private void saveData() {

        Map<String, Object> documentMap = new HashMap<>();
        String path="C:\\Users\\Administrator\\Downloads\\Picsart_23-04-22_22-138-37-352.jpg";
        try {
            documentMap.put("Name", nameInput.getText());
            documentMap.put("Cnic", cnicInput.getText());
            documentMap.put("Father Name", fatherNameInput.getText());
            documentMap.put("Father Cnic", fatherCnicInput.getText());
            documentMap.put("Phone No", phoneNoInput.getText());
            documentMap.put("Blood Group", bloodGroupsList.getSelectedItem());
            documentMap.put("city", "Lahore");
            documentMap.put("Image", mongoConnect.storeImage(path));
            documentMap.put("Date of Birth",selectedDate(true));
            documentMap.put("userID",Integer.parseInt(userIDText.getText()));
            documentMap.put("password", BCrypt.hashpw(String.valueOf(passwordText.getPassword()), BCrypt.gensalt()));
            documentMap.put("Date of Joining", selectedDate(false));
            conncetionUsers.createDocument(documentMap);
            conncetionUsers.updateId("userID",true);
            userIDText.setText(String.valueOf(conncetionUsers.updateId("userID",false)));
            JOptionPane.showMessageDialog(null,"Form Submitted Successfully");
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Form ");
            System.out.println(ex);
        }
    }

    private String selectedDate(boolean is) {
        if(is) {
            Date selectedDate = dateOfBirthInput.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(selectedDate);
        }
        else {
            Date selectedDate = dateOfJoining.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(selectedDate);
        }
    }

    private JLabel addImage(){
        JLabel pic=new JLabel();
        ImageIcon imageIcon = new ImageIcon("E:\\Programms\\Java\\ACP-Tasks\\JAVA project\\Images\\1675105387954.jpeg"); // Replace with the actual path to your image
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(170, 160, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        pic.setText("");
        pic.setIcon(scaledImageIcon);
        return pic;
    }

    private void addDriverInfo(){


        name=new JLabel("Name");
        name.setBounds (65+ xalignD, 110+ yalignD, 100, 30);
        User_Form.add(name);

        nameInput=new JTextField();
        nameInput.setBounds (210+ xalignD, 110+ yalignD, 170, 30);
        User_Form.add(nameInput);

        picture=addImage();
        picture.setBounds (720+ xalignD, 65-10, 170, 160);
        picture.setBorder(new LineBorder(Color.gray, 2, true));
        User_Form.add(picture);

        cnic=new JLabel("Cnic No :");
        cnic.setBounds (415+ xalignD, 110+ yalignD, 100, 25);
        User_Form.add(cnic);

        cnicInput=new JTextField();
        cnicInput.setBounds (540+ xalignD, 110+ yalignD, 170, 30);
        User_Form.add(cnicInput);

        fatherName=new JLabel("Father Name");
        fatherName.setBounds (65+ xalignD, 155+ yalignD, 100, 30);
        User_Form.add(fatherName);

        fatherNameInput=new JTextField();
        fatherNameInput.setBounds (210+ xalignD, 155+ yalignD, 170, 30);
        User_Form.add(fatherNameInput);

        fatherCnic=new JLabel("Father CNIC No :");
        fatherCnic.setBounds (415+ xalignD, 155+ yalignD, 100, 30);
        User_Form.add(fatherCnic);

        fatherCnicInput=new JTextField();
        fatherCnicInput.setBounds (540+ xalignD, 155+ yalignD, 170, 30);
        User_Form.add(fatherCnicInput);

        dateofBirth=new JLabel("Date of Birth");
        dateofBirth.setBounds (65+ xalignD, 200+ yalignD, 100, 30);
        User_Form.add(dateofBirth);

        dateOfBirthInput=new JDateChooser();
        dateOfBirthInput.getDateEditor().setEnabled(false);
        dateOfBirthInput.setBounds (210+ xalignD, 200+ yalignD, 170, 30);
        User_Form.add(dateOfBirthInput);

        age=new JLabel("AGE");
        age.setBounds (415+ xalignD, 200+ yalignD, 100, 25);
        User_Form.add(age);

        ageLabel=new JLabel("0");
        ageLabel.setBounds (540+ xalignD, 200+ yalignD, 170, 30);
        User_Form.add(ageLabel);

        phoneNo=new JLabel("Phone No : ");
        phoneNo.setBounds (415+ xalignD, 245+ yalignD, 100, 25);
        User_Form.add(phoneNo);

        phoneNoInput=new JTextField();
        phoneNoInput.setBounds (540+ xalignD, 245+ yalignD, 170, 30);
        User_Form.add(phoneNoInput);

        bloodGroup=new JLabel("Blood Group : ");
        bloodGroup.setBounds (65+ xalignD, 245+ yalignD, 100, 25);
        User_Form.add(bloodGroup);

        String[] items = {"A+", "A-", "B+","B-","O+","O-","AB+","AB-"};

        bloodGroupsList= new JComboBox<>(items);
        bloodGroupsList.setBounds (210+ xalignD, 245+ yalignD, 170, 30);
        User_Form.add(bloodGroupsList);


        password = new JLabel ("Enter Password : ");
        rePassword = new JLabel ("Re-Enter Your Password : ");
        passwordText = new JPasswordField (5);
        rePasswordText = new JPasswordField (5);
        userID = new JLabel ("User ID :");
        userIDText = new JLabel (String.valueOf(conncetionids.updateId("userID",true)));

        User_Form.add (password);
        User_Form.add (rePassword);
        User_Form.add (passwordText);
        User_Form.add (rePasswordText);
        User_Form.add (userID);
        User_Form.add (userIDText);

        dateOfIssue1=new JLabel("Date of Joining");
        User_Form.add(dateOfIssue1);
        dateOfJoining = new JDateChooser();
        dateOfJoining.getDateEditor().setEnabled(false);
        dateOfJoining.setBounds (210+ xalignD, 200+ yalignD, 170, 30);
        User_Form.add(dateOfJoining);

        password.setBounds (230, 350, 150, 30);
        rePassword.setBounds (230, 390, 150, 30);
        passwordText.setBounds (445, 350, 130, 30);
        rePasswordText.setBounds (445, 390, 130, 30);
        userID.setBounds (230, 310, 100, 25);
        userIDText.setBounds (445, 310, 100, 25);
        userIDText.setBorder(new LineBorder(Color.gray, 2, true));
        Font f2 = new Font("Arial", Font.BOLD, 16);
        userIDText.setFont(f2);
        userIDText.setHorizontalAlignment(SwingConstants.CENTER);
        dateOfIssue1.setBounds (230, 430, 100, 30);
        dateOfJoining.setBounds(445, 430, 130, 30);



        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);


        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Learner Holder Information");
        titledBorder.setTitleColor(Color.BLACK);
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleFont(labelFont);

        User_Form.setBorder(titledBorder);



    }

    String currentDate(Boolean interval){


        LocalDate currentDate = LocalDate.now();
        if (interval){
            currentDate= currentDate.plusMonths(6);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return currentDate.format(formatter);

    }

    public static String  calculateAge(String dateOfBirth) {
        LocalDate birthDate = LocalDate.parse(dateOfBirth);

        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(birthDate, currentDate);

        return String.valueOf(period.getYears());
    }


}
