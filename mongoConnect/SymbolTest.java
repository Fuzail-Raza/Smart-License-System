import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.HashMap;

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
    private int timeAllow = 100;
    String correctOption="";
    String selectedOption="";
    public SymbolTest(){
        iniGUI();
        ImageIcon imageIcon = new ImageIcon("E:\\Programms\\Java\\ACP-Tasks\\JAVA project\\Images\\roundabout.png"); // Replace with the actual path to your image
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        symbolLabel.setIcon(scaledImageIcon);


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

        sysmbolTest=addQuestion();
        testArea.add(sysmbolTest);
        testArea.setVisible(true);

        initiallizeTestCheck();

        mainFrame.setSize(720,600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);




    }

    private void initiallizeTestCheck() {
        for (int i=0;i<10;i++) {
            testCheck.put(i,false);
        }
    }

    JLabel addPicture(){
        JLabel pic=new JLabel();
        ImageIcon imageIcon = new ImageIcon("E:\\Programms\\Java\\ACP-Tasks\\JAVA project\\Images\\roundabout.png"); // Replace with the actual path to your image
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(130, 120, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        pic.setText("");
        pic.setIcon(scaledImageIcon);
        return pic;
    }

    private JPanel addQuestion(){

        JPanel tempTestPanel=new JPanel(new GridLayout(7,2));

        optionCheck=new ButtonGroup();

        questionNoLabel=new JLabel("Question No "+ (questionNo+1));
        tempTestPanel.add(questionNoLabel);

        timeLabel=new JLabel("Time : 10:00");
        tempTestPanel.add(timeLabel);

        questionLabel=new JLabel("Which Symbols is this ? ");
        tempTestPanel.add(questionLabel);

        symbolLabel=addPicture();
        tempTestPanel.add(symbolLabel);


        option1Label=new JRadioButton("Turn Left");
        tempTestPanel.add(option1Label);

        option2Label=new JRadioButton("Turn Right");
        tempTestPanel.add(option2Label);

        option3Label=new JRadioButton("Round About");
        tempTestPanel.add(option3Label);

        option4Label=new JRadioButton("Stop");
        tempTestPanel.add(option4Label);

        correctOption=option3Label.getText();

        option1Label.addActionListener(radioButtonListner);
        option2Label.addActionListener(radioButtonListner);
        option3Label.addActionListener(radioButtonListner);
        option4Label.addActionListener(radioButtonListner);

        optionCheck.add(option1Label);
        optionCheck.add(option2Label);
        optionCheck.add(option3Label);
        optionCheck.add(option4Label);

        prevButton=new JButton("Previous");
        tempTestPanel.add(prevButton);

        nextButton=new JButton("Next");
        tempTestPanel.add(nextButton);

        submitButton =new JButton("Submit");
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
                score=0;
            }

            questionNoLabel.setText("Question No "+ (questionNo+1));
        }
    };
    ActionListener radioButtonListner = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedOption=e.getActionCommand();
        }
    };

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
