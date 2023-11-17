package driverForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import mongoPackage.mongoConnect;

public class DrivingInfo extends JFrame {

    private JFrame mainFrame;
    private Container Driving_Form;
    private JPanel driverInfo;
    private JPanel learnerPanel;
    private JTextField dateOfBirthInput;
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
    private JLabel learnerNo2;
    private JLabel learnerNo2label;
    private JLabel learnerNo3;
    private JLabel learnerNo3label;
    private JLabel dateOfIssue2;
    private JLabel dateOfIssue3;
    private JLabel dateOfIssue2Label;
    private JLabel dateOfIssue3Label;
    private JLabel dateOfExpiry2;
    private JLabel dateOfExpiry3;
    private JLabel dateOfExpiry2Label;
    private JLabel dateOfExpiry3Label;
    private JLabel type2;
    private JLabel type3;
    private JList type2List;
    private JList type3List;
    private JSeparator separator3;
    private JCheckBox checkBox2;
    private JButton submitButton;
    private JLabel picture;
    private JSeparator separator1;
    private JLabel learnerNo1;
    private JLabel dateOfIssue1;
    private JLabel dateOfIssue1Label;
    private JLabel dateOfExpiry1;
    private JLabel dateOfExpiry1Label;
    private JLabel type1;
    private JComboBox<String> type1List;
    private JLabel learnerNo1Label;
    private JSeparator separator2;
    private JCheckBox checkBox1;
    private JPanel learnerSelectPanel;
    JPanel addType;


    public DrivingInfo() {

//        checkBox1.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    // Checkbox is selected (checked)
//                    performFunction(true);
//                } else {
//                    // Checkbox is deselected (unchecked)
//                    performFunction(false);
//                }
//            }
//        });

        initGUI();
    }



    void initGUI(){

        mainFrame=new JFrame();
        mainFrame.setTitle("Driver Info");

        Driving_Form=mainFrame.getContentPane();
        Driving_Form.setLayout(new GridLayout(5,1));

        driverInfo=addDriverInfo();

        Driving_Form.add(driverInfo);

        Driving_Form.add(new JLabel());

        separator1=new JSeparator();
        Driving_Form.add(separator1);

        learnerPanel=addLearner();
        Driving_Form.add(learnerPanel);

        submitButton=new JButton("Submit Now");

        Driving_Form.add(submitButton);

        mainFrame.setVisible(true);
        mainFrame.setSize(720,600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    mongoConnect userData=new mongoConnect();
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
                documentMap.put("Image", userData.storeImage(path));
                documentMap.put("Date of Birth",dateOfBirthInput.getText());
                documentMap.put("Date of Issue",dateOfIssue1Label.getText());
                documentMap.put("Date of Expiry",dateOfExpiry1Label.getText());
                documentMap.put("Type",type1List.getSelectedItem());
                userData.createDocument(documentMap);

                JOptionPane.showMessageDialog(null,"Form Submitted Successfully");
            }
            catch (Exception ex){
                System.out.println(ex);
            }
            }
        });

    }
    private JLabel addImage(){
        JLabel pic=new JLabel();
        ImageIcon imageIcon = new ImageIcon("E:\\Programms\\Java\\ACP-Tasks\\JAVA project\\Images\\1675105387954.jpeg"); // Replace with the actual path to your image
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(130, 120, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        pic.setText("");
        pic.setIcon(scaledImageIcon);
        return pic;
    }
    private JPanel addDriverInfo(){

        JPanel infoTemp= new JPanel(new GridLayout(4,4));

        name=new JLabel("Name");
        infoTemp.add(name);

        nameInput=new JTextField();
        infoTemp.add(nameInput);

        picture=addImage();
        infoTemp.add(picture);

        cnic=new JLabel("Cnic No :");
        infoTemp.add(cnic);

        cnicInput=new JTextField();
        infoTemp.add(cnicInput);

        fatherName=new JLabel("Father Name");
        infoTemp.add(fatherName);

        fatherNameInput=new JTextField();
        infoTemp.add(fatherNameInput);

        fatherCnic=new JLabel("Father CNIC No :");
        infoTemp.add(fatherCnic);

        fatherCnicInput=new JTextField();
        infoTemp.add(fatherCnicInput);

        dateofBirth=new JLabel("Date of Birth");
        infoTemp.add(dateofBirth);

        dateOfBirthInput=new JTextField();
        infoTemp.add(dateOfBirthInput);

        age=new JLabel("AGE");
        infoTemp.add(age);

        ageLabel=new JLabel("0");
        infoTemp.add(ageLabel);

        phoneNo=new JLabel("Phone No : ");
        infoTemp.add(phoneNo);

        infoTemp.add(phoneNoInput=new JTextField());

        bloodGroup=new JLabel("Blood Group : ");
        infoTemp.add(bloodGroup);

        String[] items = {"A+", "A-", "B+","B-","O+","O-","AB+","AB-"};

         bloodGroupsList= new JComboBox<>(items);

        infoTemp.add(bloodGroupsList);



        return infoTemp;

    }

    private JPanel addLearner(){

        JPanel tempAddLearner=new JPanel(new GridLayout(2,4));

        learnerNo1=new JLabel("Leaner No : ");
        tempAddLearner.add(learnerNo1);

        learnerNo1Label = new JLabel("00000");
        tempAddLearner.add(learnerNo1Label);

        dateOfIssue1=new JLabel("Date of issue");
        tempAddLearner.add(dateOfIssue1);

        dateOfIssue1Label=new JLabel(currentDate(false));
        tempAddLearner.add(dateOfIssue1Label);

        type1=new JLabel("Type");
        tempAddLearner.add(type1);

        String[] types = {"M.Cycle","Car/Jeep","LTV","HTV"};
        type1List=new JComboBox<>(types);
        tempAddLearner.add(type1List);

        dateOfExpiry1=new JLabel("Date of Expiry");
        tempAddLearner.add(dateOfExpiry1);

        dateOfExpiry1Label=new JLabel(currentDate(true));
        tempAddLearner.add(dateOfExpiry1Label);

        checkBox1=new JCheckBox("Add");
//        tempAddLearner.add(checkBox1);


        return tempAddLearner;

    }

    private void performFunction(boolean isEnabled) {
        // Your functionality based on checkbox state
        if (isEnabled) {

            JPanel addType=copyPanel(learnerSelectPanel);
            addType.setVisible(true);
            Driving_Form.add(addType);


        } else {
            addType.setVisible(false);
        }
    }

    String currentDate(Boolean interval){


        LocalDate currentDate = LocalDate.now();
        if (interval){
            currentDate= currentDate.plusMonths(6);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return currentDate.format(formatter);

    }
    private JPanel copyPanel(JPanel originalPanel) {
        JPanel copiedPanel = new JPanel();
        copiedPanel.setLayout(new GridLayout());
        copiedPanel.setBorder(BorderFactory.createTitledBorder("Copied Panel"));

        // Copy components from the original panel to the copied panel
        for (Component component : originalPanel.getComponents()) {
            if (component instanceof JLabel) {
                // Example: Copy JLabel
                JLabel originalLabel = (JLabel) component;
                JLabel copiedLabel = new JLabel(originalLabel.getText());
                copiedPanel.add(copiedLabel);
            } else if (component instanceof JTextField) {
                // Example: Copy JTextField
                JTextField originalTextField = (JTextField) component;
                JTextField copiedTextField = new JTextField(originalTextField.getText(), originalTextField.getColumns());
                copiedPanel.add(copiedTextField);
            } else if (component instanceof JButton) {
                // Example: Copy JButton
                JButton originalButton = (JButton) component;
                JButton copiedButton = new JButton(originalButton.getText());
                copiedPanel.add(copiedButton);
            }
            // Add more cases for other types of components if needed
        }

        return copiedPanel;
    }
    public static void main(String[] args) {
        DrivingInfo us=new DrivingInfo();

    }
}
