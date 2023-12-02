import mongoPackage.mongoConnect;
import org.bson.Document;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class AddQuestion{

    JFrame mainFrame;
    JPanel questionPanel;
    JLabel questionLabel;
    JLabel symbolLabel;
    JTextField questionText;
    JTextField[] optionText;
    JButton submitButton;
    JButton addSymbol;
    JRadioButton[] correctOption;
    ButtonGroup correct;
    String correctAnswer;
    String selectedFilePath;
    JLabel heading;
    JLabel correctOptionLabel;
    JLabel correctOptionText;
    AddQuestion(){
        initGUI();
    }
    void initGUI(){

        mainFrame =new JFrame();
        mainFrame.setTitle("Driving License");
        questionPanel =new JPanel(null);

        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);


        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, "Driving License User LOGIN");
        titledBorder.setTitleColor(Color.BLACK);
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleFont(labelFont);

        questionPanel.setBorder(titledBorder);

        Font f2 = new Font("Arial", Font.BOLD, 14);

        heading = new JLabel ("Question Addition for Test");
        heading.setFont(labelFont);
        questionPanel.add(heading);
        questionLabel=new JLabel("Question :-");
        questionLabel.setFont(f2);
        questionText=new JTextField(10);
        questionPanel.add(questionLabel);
        questionPanel.add(questionText);
        questionPanel.add(new JLabel("Correct Option"));
        symbolLabel=new JLabel("");
        questionPanel.add(symbolLabel);
        optionText=new JTextField[5];
        correctOption=new JRadioButton[4];
        correct=new ButtonGroup();
        correctOptionLabel = new JLabel ("Correct option");
        correctOptionLabel.setFont(f2);
        correctOptionText = new JLabel ("---");
        correctOptionText.setFont(f2);
        questionPanel.add(correctOptionLabel);
        questionPanel.add(correctOptionText);
        addOptions();

        correctAnswer="";

        addSymbol=new JButton("Upload Symbol");
        addSymbol.setBorder(new LineBorder(Color.gray, 2, true));
        questionPanel.add(addSymbol);
        submitButton=new JButton("Submit");
        submitButton.setBorder(new LineBorder(Color.gray, 2, true));
        questionPanel.add(submitButton);

        setPositions();

        mainFrame.add(questionPanel);
        mainFrame.setVisible(true);
        mainFrame.setSize(790, 480);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isFormValid()) {
                    return;
                }
                mongoConnect admin=new mongoConnect("Driving_Center","symbolTest");
                Map<String, Object> documentMap = new HashMap<>();
                try {
                    Document newQuestion = new Document("Question", questionText.getText())
                         .append("questionID","00")
                        .append("Option1", optionText[0].getText())
                        .append("Option2", optionText[1].getText())
                        .append("Option3", optionText[2].getText())
                        .append("Option4",optionText[3].getText())
                        .append("Symbol",mongoConnect.storeImage(selectedFilePath))
                        .append("Correct",correctAnswer);
                    if( admin.createDocument(newQuestion)) {
                        JOptionPane.showMessageDialog(mainFrame, "Symbol Added Successfully");
                    }
                    else {
                        JOptionPane.showMessageDialog(mainFrame, "Symbol Not Added Successfully");
                    }
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        addSymbol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "png", "jpg");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(mainFrame);



                if (result == JFileChooser.APPROVE_OPTION) {
                    // User selected a file
                    selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();

                    if (isImageFile(selectedFilePath)) {
                        try {
                            byte[] imageData = mongoConnect.storeImage(selectedFilePath);
                            ImageIcon imageIcon = new ImageIcon(imageData);
                            Image scaledImage = imageIcon.getImage().getScaledInstance(190, 165, Image.SCALE_SMOOTH);
                            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                            symbolLabel.setBorder(new LineBorder(Color.gray, 2, true));
                            symbolLabel.setIcon(scaledImageIcon);

                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Please select a valid image file (png or jpg).");
                    }
                }
            }
        });

    }

    private void setPositions() {

        addSymbol.setBounds (575, 325, 170, 30);
        submitButton.setBounds (213, 385, 240, 40);
        correctOption[0].setBounds (105, 195, 170, 30);
        correctOption[1].setBounds (105, 245, 170, 30);
        correctOption[2].setBounds (105, 290, 170, 30);
        correctOption[3].setBounds (105, 335, 170, 30);
        optionText[0].setBounds (335, 195, 170, 30);
        optionText[1].setBounds (335, 245, 170, 30);
        optionText[2].setBounds (335, 290, 170, 30);
        optionText[3].setBounds (335, 335, 170, 30);
        questionLabel.setBounds (105, 105, 133, 30);
        questionText.setBounds (260, 105, 300, 30);
        symbolLabel.setBounds (565, 155, 190, 165);
        heading.setBounds (270, 30, 350, 50);
        correctOptionLabel.setBounds (130, 150, 130, 25);
        correctOptionText.setBorder(new LineBorder(Color.gray, 2, true));
        correctOptionText.setHorizontalAlignment(SwingConstants.CENTER);
        correctOptionText.setBounds (322, 145, 195, 30);
    }

    private boolean isFormValid() {
        if (questionText.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please fill out Question Description.");
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (optionText[i].getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Please fill out Option "+(i+1)+".");
                return false;
            }
        }

        if (selectedFilePath == null || selectedFilePath.trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please Add Symbol Picture.");
            return false;
        }
        if (correctAnswer == null || correctAnswer.trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please Select Correct Answer of the Question.");
            return false;
        }

        return true;
    }
    private static boolean isImageFile(String filePath) {
        String lowercaseFilePath = filePath.toLowerCase();
        return lowercaseFilePath.endsWith(".png") || lowercaseFilePath.endsWith(".jpg");
    }

    void addOptions(){
        for(int i=0;i<4;i++) {
            optionText[i] = new JTextField(20);
            correctOption[i]=new JRadioButton("Option "+(i+1));
            Font f2 = new Font("Arial", Font.BOLD, 13);
            correctOption[i].addActionListener(buttonListener);
            correctOption[i].setFont(f2);;
            questionPanel.add(correctOption[i]);
            questionPanel.add(optionText[i]);
            correct.add(correctOption[i]);
        }


    }

    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            System.out.println("Action Command: " + actionCommand);
            correctAnswer=actionCommand;

            switch (actionCommand) {
                case "Option 1":
                    correctAnswer = optionText[0].getText();
                    break;
                case "Option 2":
                    correctAnswer = optionText[1].getText();
                    break;
                case "Option 3":
                    correctAnswer = optionText[2].getText();
                    break;
                case "Option 4":
                    correctAnswer = optionText[3].getText();
                    break;
            }
            correctOptionText.setText(correctAnswer);
            System.out.println(correctAnswer);
        }
    };




}


public class addSymbolquestion {

    public static void main(String[] args) {
        new AddQuestion();

    }

}
