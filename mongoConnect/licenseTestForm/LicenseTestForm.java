package licenseTestForm;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.print.*;
import java.time.LocalDate;
import java.time.Period;

import mongoPackage.mongoConnect;
import org.bson.Document;
import org.bson.types.Binary;

class TestForm {

    private JFrame mainFrame;

    private JTextField textField1;
    private JButton detailsFetchButton;
    JButton print;
    private JLabel name;
    private JLabel fatherName;
    private JLabel cnic;
    private JLabel fatherCnic;
    private JLabel fatherCniclabel;
    private JLabel dateofBirth;
    private JLabel age;
    private JLabel ageLabel;
    private JLabel phoneNo;
    private JLabel bloodGroup;
    private JLabel dateOfIssue;
    private JLabel dateOfExpiry;
    private JLabel dateOfIssueLabel;
    private JLabel dateOfExpiryLabel;
    private JRadioButton symbolPassCheckBox;
    private JRadioButton symbolFailCheckBox;
    private JRadioButton drivingPassCheckBox;
    private JRadioButton drivingFailCheckBox;
    private JTextArea remarksTextArea;
    Container licenseTestForm;
    private JPanel infoPanel;

    private JPanel testDetailPanel;
    private JButton submitButton;
    private JLabel learnerInput;
    private JLabel nameLabel;
    private JLabel cnicLabel;
    private JLabel type;
    private JLabel fatherNameLabel;
    private JLabel phoneNoLabel;
    private JLabel bloodGroupLabel;
    private JLabel typeLabel;
    private JLabel reamainingValidity;
    private JLabel reamainingValidityLabel;
    private JLabel symbolTest;
    private JLabel drivingTest;
    private JLabel reamarks;
    private JLabel picture;
    private JLabel dateOfBirthLabel;

    private JSeparator separator;

    private ButtonGroup buttonGroupSymbol = new ButtonGroup();

    private ButtonGroup buttonGroupDriving = new ButtonGroup();
    public TestForm() {

        initGUI();
    }

    void initGUI() {
        mainFrame=new JFrame();
        mainFrame.setTitle("License Test Form");

        licenseTestForm=mainFrame.getContentPane();
        licenseTestForm.setLayout(new GridLayout(3,1));

        infoPanel=addInfoPanel();
        licenseTestForm.add(infoPanel);

        separator=new JSeparator();
        licenseTestForm.add(separator);

        testDetailPanel=testDetail();
        licenseTestForm.add(testDetailPanel);

        mainFrame.setSize(720,600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        licenseTestForm.setVisible(true);

        mainFrame.setVisible(true);

        addActionListeners();
    }

    public static String calculateExpiryDuration( String dateOfExpiry){

        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = LocalDate.parse(dateOfExpiry);

        Period expiryDuration = Period.between(currentDate, expiryDate);;

        return String.valueOf(expiryDuration.getYears()*12*30 + expiryDuration.getMonths() * 30 + expiryDuration.getDays());
    }

    public static String  calculateAge(String dateOfBirth) {
        LocalDate birthDate = LocalDate.parse(dateOfBirth);

        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(birthDate, currentDate);

        return String.valueOf(period.getYears());
    }
    JLabel addPicture(){
        JLabel pic=new JLabel();
        ImageIcon imageIcon = new ImageIcon("E:\\Programms\\Java\\ACP-Tasks\\JAVA project\\Images\\1675105387954.jpeg"); // Replace with the actual path to your image
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(130, 120, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        pic.setText("");
        pic.setIcon(scaledImageIcon);
        return pic;
    }

    JPanel addInfoPanel(){

        JPanel infoTemp= new JPanel(new GridLayout(7,4));
        learnerInput=new JLabel("Learner No");
        infoTemp.add(learnerInput);

        textField1=new JTextField();

        infoTemp.add(textField1);


        infoTemp.add(new JLabel());

        detailsFetchButton =new JButton("Retrieve");
        infoTemp.add(detailsFetchButton);

        picture=addPicture();
        picture.setSize(300,200);
        infoTemp.add(picture);


        name=new JLabel("Name : ");
        infoTemp.add(name);

        nameLabel=new JLabel("---");
        infoTemp.add(nameLabel);

        cnic=new JLabel("Cnic No :");
        infoTemp.add(cnic);

        cnicLabel=new JLabel("---");
        infoTemp.add(cnicLabel);

        fatherName=new JLabel("Father Name");
        infoTemp.add(fatherName);

        fatherNameLabel=new JLabel("---");
        infoTemp.add(fatherNameLabel);

        fatherCnic=new JLabel("Father CNIC No :");
        infoTemp.add(fatherCnic);

        fatherCniclabel=new JLabel("---");
        infoTemp.add(fatherCniclabel);

        dateofBirth=new JLabel("Date of Birth");
        infoTemp.add(dateofBirth);

        dateOfBirthLabel=new JLabel("00-00-0000");
        infoTemp.add(dateOfBirthLabel);

        age=new JLabel("AGE");
        infoTemp.add(age);

        ageLabel=new JLabel("0");
        infoTemp.add(ageLabel);

        phoneNo=new JLabel("Phone No : ");
        infoTemp.add(phoneNo);

        phoneNoLabel=new JLabel("0000-0000000");
        infoTemp.add(phoneNoLabel);

        bloodGroup=new JLabel("Blood Group : ");
        infoTemp.add(bloodGroup);

        bloodGroupLabel=new JLabel("--");
        infoTemp.add(bloodGroupLabel);

        type=new JLabel("Type");
        infoTemp.add(type);

        typeLabel=new JLabel("---");
        infoTemp.add(typeLabel);

        dateOfIssue=new JLabel("Date of Issue : ");
        infoTemp.add(dateOfIssue);

        dateOfIssueLabel=new JLabel("0-0-0000");
        infoTemp.add(dateOfIssueLabel);

        dateOfExpiry=new JLabel("Date of Expiry : ");
        infoTemp.add(dateOfExpiry);

        dateOfExpiryLabel=new JLabel("0-0-0000");
        infoTemp.add(dateOfExpiryLabel);

        reamainingValidity=new JLabel("Validity Remaining : ");
        infoTemp.add(reamainingValidity);

        reamainingValidityLabel=new JLabel("0 Days");
        infoTemp.add(reamainingValidityLabel);



        return infoTemp;
    }


    JPanel testDetail(){

        JPanel testTemp=new JPanel();
        testTemp.setLayout(new GridLayout(4,3));

        symbolTest =new JLabel("Symbol Test : ");
        testTemp.add(symbolTest);

        testTemp.add(new JLabel());

        buttonGroupSymbol=new ButtonGroup();

        symbolPassCheckBox=new JRadioButton("Pass");
        buttonGroupSymbol.add(symbolPassCheckBox);
        testTemp.add(symbolPassCheckBox);

        symbolFailCheckBox=new JRadioButton("Fail");
        buttonGroupSymbol.add(symbolFailCheckBox);
        testTemp.add(symbolFailCheckBox);


        drivingTest=new JLabel("Driving Test : ");
        testTemp.add(drivingTest);

        testTemp.add(new JLabel());

        buttonGroupDriving = new ButtonGroup();

        drivingPassCheckBox=new JRadioButton("Pass");
        buttonGroupDriving.add(drivingPassCheckBox);
        testTemp.add(drivingPassCheckBox);

        drivingFailCheckBox=new JRadioButton("Fail");
        buttonGroupDriving.add(drivingFailCheckBox);
        testTemp.add(drivingFailCheckBox);



        reamarks =new JLabel("Remarks");
        testTemp.add(reamarks);

        remarksTextArea=new JTextArea();
        testTemp.add(remarksTextArea);

        testTemp.add(new JLabel());
        testTemp.add(new JLabel());
        testTemp.add(new JLabel());
        testTemp.add(new JLabel());

        submitButton=new JButton("Submit");
        testTemp.add(submitButton);

        print=new JButton("Print");
        testTemp.add(print);


        return testTemp;
    }

    void addActionListeners() {
        ActionListener buttonListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(e.getActionCommand().equals("Retrieve")){

                    mongoConnect userInfo=new mongoConnect("Driving_Center","testing");
                    try {
                        Document userFetchData = userInfo.readDocument("Cnic", textField1.getText().trim());
                        nameLabel.setText(userFetchData.getString("Name"));
                        cnicLabel.setText(userFetchData.getString("Cnic"));
                        fatherNameLabel.setText(userFetchData.getString("Father Name"));
                        fatherCniclabel.setText(userFetchData.getString("Father Cnic"));
                        dateOfBirthLabel.setText(userFetchData.getString("Date of Birth"));
                        dateOfIssueLabel.setText(userFetchData.getString("Date of Issue"));
                        dateOfExpiryLabel.setText(userFetchData.getString("Date of Expiry"));
                        phoneNoLabel.setText(userFetchData.getString("Phone No"));
                        ageLabel.setText(calculateAge(userFetchData.getString("Date of Birth")));
                        typeLabel.setText(userFetchData.getString("Type"));
                        String expiry = String.valueOf(calculateExpiryDuration(userFetchData.getString("Date of Expiry")));
                        reamainingValidityLabel.setText(String.valueOf(expiry + " Days"));
                        bloodGroupLabel.setText(userFetchData.getString("Blood Group"));
                        byte[] imageData = userInfo.fetchImage(userFetchData.get("Image", Binary.class));
                        ImageIcon imageIcon = new ImageIcon(imageData);
                        picture.setIcon(imageIcon);
                    }
                    catch (Exception ex){
                        System.out.println("User Not Found");
                    }

                }
                else if (e.getActionCommand().equals("Submit")){
                    JOptionPane.showMessageDialog(mainFrame, "Form Submitted!");
                }
                else if(e.getActionCommand().equals("Print")){

                        printDocument();

                }

            }

        };
        detailsFetchButton.addActionListener(buttonListener);
        print.addActionListener(buttonListener);
        submitButton.addActionListener(buttonListener);


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

            // Draw your content here for printing
            // Header
            Font originalFont = g.getFont();
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("License Test Form", 200, 50);
            g.setFont(originalFont);
            // Info Panel content
            String nameToPrint = nameLabel.getText().length() > 15 ? nameLabel.getText().substring(0, 15) : nameLabel.getText();
            g.drawString("Name: " + nameToPrint, 100, 100);
            Icon icon = picture.getIcon();
            if (icon instanceof ImageIcon) {
                Image image = ((ImageIcon) icon).getImage();
                int imageWidth = 100;
                int imageHeight = 100;
                // Draw the image at coordinates (150, 160)
                g.drawImage(image, 480, 50, imageWidth, imageHeight,this);
            } else {
                // Handle the case when the icon is not an ImageIcon
                g.drawString("No image available", 150, 160);
            }
            g.drawString("CNIC: " + cnicLabel.getText(), 300, 100);
            // Add other fields from the info panel as needed

            g.drawString("Father Name : " + fatherNameLabel.getText(), 100, 130);
            g.drawString("Father CNIC : " + fatherCniclabel.getText(), 300, 130);
            g.drawString("Date of Birth: " + dateOfBirthLabel.getText(), 100, 160);
            g.drawString("AGE : " + ageLabel.getText(), 300, 160);
            g.drawString("Phone No : " + phoneNoLabel.getText(), 100, 190);
            g.drawString("Blood Group : " + bloodGroupLabel.getText(), 300, 190);
            g.drawString("Type : " + typeLabel.getText(), 100, 220);
            g.drawString("Validity Remaining : " + reamainingValidityLabel.getText(), 300, 220);
            g.drawString("Date of Issue : " + dateOfIssueLabel.getText(), 100, 250);
            g.drawString("Date of Expiry: " + dateOfExpiryLabel.getText(), 300, 250);
            // Separator
            g.drawLine(100, 270, 500, 270);

//             Test Detail content

            g.drawString("Symbol Test: " ,100, 320);

            drawCheckbox(g, 230, 308, symbolPassCheckBox.isSelected());
            g.drawString("Pass " ,260, 320);

            drawCheckbox(g, 310, 308, symbolFailCheckBox.isSelected());
            g.drawString("Fail" ,340, 320);

            g.drawString("Driving Test: " ,100, 370);

            drawCheckbox(g, 230, 358, drivingPassCheckBox.isSelected());
            g.drawString("Pass " ,260, 370);

            drawCheckbox(g, 310, 358, drivingFailCheckBox.isSelected());
            g.drawString("Fail" ,340, 370);


            g.drawString("Remarks: " + remarksTextArea.getText(), 100, 410);
            g.drawLine(130, 440, 500, 440);
            g.drawLine(110, 470, 500, 470);
            g.drawLine(110, 500, 500, 500);


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

}




public class LicenseTestForm {

    public static void main(String[] args) {
        TestForm us=new TestForm();


    }
}
