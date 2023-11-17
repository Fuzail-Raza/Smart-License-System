import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class SymbolTest extends JFrame{

    private JFrame mainFrame;

    private Container testArea;
    private JRadioButton option1Label;
    private JRadioButton option2Label;
    private JRadioButton option3Label;
    private JRadioButton option4Label;

    private ButtonGroup optionCheck;
    private JButton submitLabel;
    private JPanel sysmbolTest;
    private JLabel questionNoLabel;
    private JLabel timeLabel;
    private JLabel questionLabel;
    private JLabel symbolLabel;
    private LocalTime startTime;
    private int timeAllow = 10;
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

        mainFrame.setSize(720,600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);




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

        questionNoLabel=new JLabel("Question No 0");
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

        optionCheck.add(option1Label);
        optionCheck.add(option2Label);
        optionCheck.add(option3Label);
        optionCheck.add(option4Label);

        submitLabel=new JButton("Submit");
        tempTestPanel.add(submitLabel);



        return tempTestPanel;
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
