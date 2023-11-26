package driverForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import mongoPackage.mongoConnect;


public class DrivingInfo extends JFrame {

    private JFrame mainFrame;
    private Container Driving_Form;
    private JPanel driverInfo;
    private JPanel learnerPanel;
    private JDateChooser  dateOfBirthInput;
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

        dateOfBirthInput.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                handleSelectedDate();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                saveData();
                mongoConnect temp=new mongoConnect("Driving_Center","id_Collection");
                temp.updateId("learnerNo",true);
                printDocument();
            }
        });

    }
    private void handleSelectedDate() {
        Date currentDate = new Date();

        if (Integer.parseInt(calculateAge(selectedDate()))<18) {
            // The selected date is in the future
            JOptionPane.showMessageDialog(
                    null,
                    "Age must be Greater than 18.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE);

//            dateOfBirthInput.setDate(currentDate);
        }

    }
    private void saveData() {


        mongoConnect userData=new mongoConnect("Driving_Center","testing");
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
            documentMap.put("Date of Birth",selectedDate());
            documentMap.put("Date of Issue",dateOfIssue1Label.getText());
            documentMap.put("Date of Expiry",dateOfExpiry1Label.getText());
            documentMap.put("Type",type1List.getSelectedItem());
            userData.createDocument(documentMap);

            mongoConnect temp=new mongoConnect("Driving_Center","id_Collection");
            temp.updateId("learnerNo",true);

            JOptionPane.showMessageDialog(null,"Form Submitted Successfully");
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    private String selectedDate() {
        Date selectedDate = dateOfBirthInput.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(selectedDate);
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

        dateOfBirthInput=new JDateChooser();
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

        mongoConnect temp=new mongoConnect("Driving_Center","id_Collection");

        learnerNo1Label = new JLabel(String.valueOf(temp.updateId("learnerNo",false)));
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
    public static String  calculateAge(String dateOfBirth) {
        LocalDate birthDate = LocalDate.parse(dateOfBirth);

        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(birthDate, currentDate);

        return String.valueOf(period.getYears());
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

    private void printDocument() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new MyPrintable());

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
    private  class MyPrintable implements Printable, ImageObserver {

        public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());

            Font originalFont = g.getFont();
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Learner Form", 200, 50);

            Stroke originalStroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(3));

            g2d.drawRect(300, 175, 90, 23);
            String  text="Learner No :  "+ learnerNo1Label.getText();
            g.drawString(text, 180, 195);

            g2d.setStroke(originalStroke);

            g.setFont(originalFont);
            // Info Panel content
            String nameToPrint = nameInput.getText().length() > 15 ? nameInput.getText().substring(0, 15) : nameInput.getText();
            g.drawString("Name: " + nameInput.getText(), 100, 100);
            Icon icon = picture.getIcon();
            if (icon instanceof ImageIcon) {
                Image image = ((ImageIcon) icon).getImage();
                int imageWidth = 100;
                int imageHeight = 100;
                // Draw the image at coordinates (150, 160)
                g.drawImage(image, 480, 50, imageWidth, imageHeight,this);
            } else {
                // Handle the case when the icon is not an ImageIcon
                g.drawString("No image available", 480, 50);
            }
            g.drawString("CNIC: " + cnicInput.getText(), 300, 100);
            // Add other fields from the info panel as needed

            g.drawString("Father Name : " + fatherNameInput.getText(), 100, 130);
            g.drawString("AGE : " + calculateAge(selectedDate()), 300, 130);
            g.drawString("Phone No : " + phoneNoInput.getText(), 100, 160);
            g.drawString("Blood Group : " + bloodGroupsList.getSelectedItem(), 300, 160);
            g.drawString("Type : " + type1List.getSelectedItem(), 100, 220);
            g.drawString("Date of Issue : " + dateOfIssue1Label.getText(), 100, 250);
            g.drawString("Date of Expiry: " + dateOfExpiry1Label.getText(), 300, 250);

            g.drawLine(100, 270, 500, 270);

            int x = 50;
            int y = 290;
            int width = 500;
            int height = 85;


            g2d.setStroke(new BasicStroke(3));

            g2d.drawRect(x, y, width, height);

//        g2d.setStroke(originalStroke);

            text = "For First Time you can take Driving Test after 41 days of issuing Learner.";
            Font font = new Font("Arial", Font.PLAIN, 12);
            g.setFont(font);
            g.drawString(text, x + 10, y + 30);
            text="This codition doesn't Apply on Leraner Renewal !";
            g.drawString(text, x + 10, y + 50);
            g2d.drawRect(199, 345, 90, 20);
            text="Eligible For Driving Test :    "+ LocalDate.now().plusDays(41);
            g.drawString(text, x + 10, y + 70);


            return Printable.PAGE_EXISTS;
        }
        private void drawCheckbox(Graphics g, int x, int y, boolean checked) {
            // Draw a square representing the checkbox
            g.drawRect(x, y, 20, 20);

            // If checked, draw a checkmark
            if (checked) {
                g.drawLine(x + 2, y + 10, x + 8, y + 18);
                g.drawLine(x + 8, y + 18, x + 18, y + 2);
            }
        }

        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            return false;
        }
    }
    public static void main(String[] args) {
        DrivingInfo us=new DrivingInfo();

    }
}
