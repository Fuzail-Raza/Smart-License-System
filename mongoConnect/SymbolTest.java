
import mongoPackage.mongoConnect;
import org.bson.Document;
import org.bson.types.Binary;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SymbolTest {

    private JFrame mainFrame;

    private Container testArea;
    private JRadioButton option1Label;
    private JRadioButton option2Label;
    private JRadioButton option3Label;
    private JRadioButton option4Label;

    private ButtonGroup optionCheck;
    private JButton submitButton;
    private JButton nextButton;
    private JButton prevButton;
    private JPanel sysmbolTest;
    private JLabel questionNoLabel;
    private JLabel timeLabel;
    private JLabel questionLabel;
    private JLabel symbolLabel;
    private LocalTime startTime;
    HashMap<Integer, Boolean> testCheck = new HashMap<>();
    int score=0;
    int questionNo=0;
    private int timeAllow = 10000;
    String correctOption="";
    Document[] questions;
    String selectedOption="";
    public SymbolTest(){
        iniGUI();

        Timer timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateElapsedTime();
            }
        });
        timer.start();


    }


    void iniGUI(){

        mainFrame=new JFrame();
        mainFrame.setTitle("Symbol Test");

        testArea=mainFrame.getContentPane();

        fetchQuestions();
        sysmbolTest=addQuestion();
        testArea.add(sysmbolTest);
        testArea.setVisible(true);

        initiallizeTestCheck();

        mainFrame.setSize(784, 449);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);




    }

    private void fetchQuestions() {

        mongoConnect q1=new mongoConnect("Driving_Center","symbolTest");
        questions=new Document[10];
        questions=q1.fetchFirst10Documents();

    }

    private void initiallizeTestCheck() {
        for (int i=0;i<10;i++) {
            testCheck.put(i,false);
        }
    }


    private JPanel addQuestion(){

        JPanel tempTestPanel=new JPanel(null);

        optionCheck=new ButtonGroup();

        questionNoLabel=new JLabel("Question No "+ (questionNo+1));
        Font f1 = new Font("Arial", Font.BOLD, 15);
        questionNoLabel.setFont(f1);
        questionNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionNoLabel.setBounds(100, 40, 290, 65);
        tempTestPanel.add(questionNoLabel);

        timeLabel=new JLabel("Time : 10:00");
        timeLabel.setFont(f1);
        timeLabel.setBounds(445, 60, 85, 25);
        tempTestPanel.add(timeLabel);

        questionLabel=new JLabel(String.valueOf(questions[questionNo].get("Question")));
        Font f2 = new Font("Arial", Font.BOLD, 13);
        questionLabel.setFont(f1);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setBounds(95, 120, 290, 40);
        tempTestPanel.add(questionLabel);

        symbolLabel=new JLabel();
        byte[] imageData = mongoConnect.fetchImage(questions[questionNo].get("Symbol", Binary.class));
        ImageIcon imageIcon = new ImageIcon(imageData);
        symbolLabel.setIcon(imageIcon);
        symbolLabel.setBounds(500, 110, 265, 235);
        symbolLabel.setBorder(new LineBorder(Color.gray, 2, true));
        tempTestPanel.add(symbolLabel);


        option1Label=new JRadioButton(String.valueOf(questions[questionNo].get("Option1")));
        option1Label.setBounds(80, 175, 175, 55);
        option1Label.setFont(f2);
        tempTestPanel.add(option1Label);

        option2Label=new JRadioButton(String.valueOf(questions[questionNo].get("Option2")));
        option2Label.setBounds(315, 175, 175, 55);
        option2Label.setFont(f2);
        tempTestPanel.add(option2Label);

        option3Label=new JRadioButton(String.valueOf(questions[questionNo].get("Option3")));
        option3Label.setBounds(80, 240, 175, 55);
        option3Label.setFont(f2);
        tempTestPanel.add(option3Label);

        option4Label=new JRadioButton(String.valueOf(questions[questionNo].get("Option4")));
        option4Label.setBounds(315, 240, 175, 55);
        option4Label.setFont(f2);
        tempTestPanel.add(option4Label);

        correctOption=String.valueOf(questions[questionNo].get("Correct"));

        option1Label.addActionListener(radioButtonListner);
        option2Label.addActionListener(radioButtonListner);
        option3Label.addActionListener(radioButtonListner);
        option4Label.addActionListener(radioButtonListner);

        optionCheck.add(option1Label);
        optionCheck.add(option2Label);
        optionCheck.add(option3Label);
        optionCheck.add(option4Label);

        prevButton=new JButton("Previous");
        prevButton.setFont(f2);
        prevButton.setBounds(105, 305, 155, 40);
        prevButton.setBorder(new LineBorder(Color.gray, 2, true));
        tempTestPanel.add(prevButton);

        nextButton=new JButton("Next");
        nextButton.setFont(f2);
        nextButton.setBounds(285, 305, 155, 40);
        nextButton.setBorder(new LineBorder(Color.gray, 2, true));
        tempTestPanel.add(nextButton);

        submitButton =new JButton("Submit");
        submitButton.setFont(f2);
        submitButton.setBounds(190, 365, 155, 40);
        submitButton.setBorder(new LineBorder(Color.gray, 2, true));
        submitButton.setVisible(false);
        tempTestPanel.add(submitButton);

        nextButton.addActionListener(buttonListner);
        prevButton.addActionListener(buttonListner);
        submitButton.addActionListener(buttonListner);

        return tempTestPanel;
    }

    ActionListener buttonListner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Next")){
                if (selectedOption.equals(correctOption)){
//                    JOptionPane.showMessageDialog(null,questionNo+"True");
                    testCheck.put(questionNo,true);
                }
                else{
//                    JOptionPane.showMessageDialog(null,questionNo+"False");
                    testCheck.put(questionNo,false);
                }
                questionNo++;
                if (questionNo<10){
                    if(questionNo==9) {
                        nextButton.setEnabled(false);
                        submitButton.setVisible(true);
                    }
                    if(!prevButton.isEnabled()){
                        prevButton.setEnabled(true);
                    }
                }
                updateQuestion();
            }
            else if (e.getActionCommand().equals("Previous")) {

                if(questionNo>=0){
                    if (selectedOption.equals(correctOption)){
//                    JOptionPane.showMessageDialog(null,questionNo+"True");
                        testCheck.put(questionNo,true);
                    }
                    else{
//                    JOptionPane.showMessageDialog(null,questionNo+"False");
                        testCheck.put(questionNo,false);
                    }
                    questionNo--;
                    if (questionNo==0){
                        prevButton.setEnabled(false);
                    }
                    if(!nextButton.isEnabled()){
                        nextButton.setEnabled(true);
                    }
                }
                updateQuestion();

            } else if (e.getActionCommand().equals("Submit")) {
                if (selectedOption.equals(correctOption)){
                    JOptionPane.showMessageDialog(null,questionNo+"True");
                    testCheck.put(questionNo,true);
                }
                else{
                    JOptionPane.showMessageDialog(null,questionNo+"False");
                    testCheck.put(questionNo,false);
                }
                for (int i=0;i<10;i++) {
                    if(testCheck.get(i)){
                        score++;
                    }
                }

                JOptionPane.showMessageDialog(null,score);
                mainFrame.dispose();
//                saveResult();
                printDocument();
                score=0;
            }

        }
    };

    private void updateQuestion() {

//        JOptionPane.showMessageDialog(null,correctOption);
        questionNoLabel.setText("Question No "+ (questionNo+1));

        questionLabel.setText(String.valueOf(questions[questionNo].get("Question")));

        option1Label.setText(String.valueOf(questions[questionNo].get("Option1")));
        option2Label.setText(String.valueOf(questions[questionNo].get("Option2")));
        option3Label.setText(String.valueOf(questions[questionNo].get("Option3")));
        option4Label.setText(String.valueOf(questions[questionNo].get("Option4")));

        correctOption=String.valueOf(questions[questionNo].get("Correct"));

        byte[] imageData = mongoConnect.fetchImage(questions[questionNo].get("Symbol", Binary.class));
        ImageIcon imageIcon = new ImageIcon(imageData);
        symbolLabel.setIcon(imageIcon);


    }

    private void saveResult() {
        mongoConnect t1=new mongoConnect("Driving_Center","signTestResult");
        Map<String, Object> documentMap = new HashMap<>();
        documentMap.put("LearnerNo", "000000");
        documentMap.put("TestMarks", score);
        documentMap.put("TestDate", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        t1.createDocument(documentMap);
    }

    ActionListener radioButtonListner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedOption=e.getActionCommand();
        }
    };


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
            g.drawString("Traffic Signs Test", 200, 50);
            g.setFont(originalFont);
            // Info Panel content
//            String nameToPrint = nameInput.getText().length() > 15 ? nameInput.getText().substring(0, 15) : nameInput.getText();
            g.drawString("Name: " + "Fuzail", 100, 100);
            Icon icon = null ;//picture.getIcon();
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
            g.drawString("CNIC: " + "3520100000005", 300, 100);
            // Add other fields from the info panel as needed

            g.drawString("Father Name : " + "Fasial Majeed", 100, 130);
            g.drawString("AGE : " + "20", 300, 130);
            g.drawString("Type : " + "Car/Jeep", 100, 160);


            g.drawLine(100, 210, 500, 210);

            g2d.setStroke(new BasicStroke(3));

            g.setFont(new Font("Arial", Font.BOLD, 20));


            Stroke originalStroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(3));

            g2d.drawRect(300, 175, 90, 23);
            String  text="Learner No :   "+ "999999";
            g.drawString(text, 180, 195);

            g2d.setStroke(originalStroke);



            g.drawString("Test Result", 230, 240);
            if(score>4) {
                g.drawString("Condition : Pass" , 130, 270);
            }
            else {
                g.drawString("Condition : Fail", 130, 270);
            }

            g.drawString("Marks : " +10+"/"+score, 310, 270);
            g.setFont(originalFont);



            int rectangleWidth = 500;
            int rectangleHeight = 30;

            int squareSize = rectangleWidth / 10;

            int startX = 64;
            int startY = 285;

            // Draw the rectangle
//            g.drawRect(startX, startY, rectangleWidth, rectangleHeight);

//             Draw the divided squares
            for (int i = 0; i < 10; i++) {
                int x1 = startX + i * squareSize;
                int y1 = startY;
                g.drawRect(x1, y1, squareSize, squareSize/2);
                Font f1=new Font("Arial",Font.BOLD,18);
                g.setFont(f1);
                g.drawString("Q"+(i+1),x1+13,y1+18);
            }
            for (int i = 0; i < 10; i++) {
                int x1 = startX + i * squareSize;
                int y1 = startY+25;
                g.drawRect(x1, y1, squareSize, squareSize/2);
                if (testCheck.get(i)) {
                    drawTick(g, x1, y1, squareSize, squareSize / 2);
                } else if (!testCheck.get(i)){
                    drawCross(g, x1, y1, squareSize-10, squareSize / 2);
                }
            }

            int x = 150;
            int y = 355;
            int width = 300;
            int height = 40;



            g2d.drawRect(x, y, width, height);

            if(score<5) {


//        g2d.setStroke(originalStroke);
                text = "Retake Test After 41 Days Dated : ";
                Font font = new Font("Arial", Font.PLAIN, 12);
                g.setFont(font);
                g.drawString(text, x + 10, y + 26);
                g2d.drawRect(x + 200, y + 12, 90, 20);
                g.setFont(new Font("Arial", Font.BOLD, 13));
                text = String.valueOf(LocalDate.now().plusDays(41));
                g.drawString(text, x + 200 + 13, y + 26);
            }
            else {
                text = "Congratulations! You can now take Driving Test";
                Font font = new Font("Arial", Font.BOLD, 13);
                g.setFont(font);
                g.drawString(text, x + 3, y + 26);
            }



            return Printable.PAGE_EXISTS;
        }
        private void drawTick(Graphics g, int x, int y, int width, int height) {
            int[] xPoints = {x + width / 4, x + width / 2, x + 3 * width / 4};
            int[] yPoints = {y + height / 2, y + 3 * height / 4, y + height / 4};
            g.drawPolyline(xPoints, yPoints, 3);
        }

        private void drawCross(Graphics g, int x, int y, int width, int height) {
            g.drawLine(x+10, y, x + width-10, y + height);
            g.drawLine(x+10, y + height, x + width-10, y);
        }


        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            return false;
        }
    }

    private void updateElapsedTime() {
        if (startTime == null) {
            startTime = LocalTime.now();
        }

        LocalTime currentTime = LocalTime.now();
        int elapsedSeconds = (int) (currentTime.toSecondOfDay() - startTime.toSecondOfDay());
        int remaining = timeAllow - elapsedSeconds;

        if (remaining <= 0) {
            remaining = 0;
            sysmbolTest.setVisible(false);
        }

        int minutes = remaining / 60;
        int seconds = remaining % 60;

        // Update the timeLabel
        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    public static void main(String[] args) {
        SymbolTest us=new SymbolTest();

    }
}
