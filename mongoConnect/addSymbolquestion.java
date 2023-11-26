import mongoPackage.mongoConnect;
import org.bson.Document;

import javax.swing.*;
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
    AddQuestion(){
        initGUI();
    }
    void initGUI(){

        mainFrame =new JFrame();
        questionPanel =new JPanel(new GridLayout(7,2));
        questionLabel=new JLabel("Question :-");
        questionText=new JTextField(10);
        questionPanel.add(questionLabel);
        questionPanel.add(questionText);
        questionPanel.add(new JLabel("Correct Option"));
        symbolLabel=new JLabel("");
        questionPanel.add(symbolLabel);
        optionText=new JTextField[5];
        correctOption=new JRadioButton[4];
        correct=new ButtonGroup();
        addOptions();

        correctAnswer="";

        addSymbol=new JButton("Upload Symbol");
        questionPanel.add(addSymbol);
        submitButton=new JButton("Submit");
        questionPanel.add(submitButton);

        mainFrame.add(questionPanel);
        mainFrame.setVisible(true);
        mainFrame.setSize(400,300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                            symbolLabel.setIcon(imageIcon);


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

    private static boolean isImageFile(String filePath) {
        String lowercaseFilePath = filePath.toLowerCase();
        return lowercaseFilePath.endsWith(".png") || lowercaseFilePath.endsWith(".jpg");
    }

    void addOptions(){
        for(int i=0;i<4;i++) {
            optionText[i] = new JTextField(20);
            correctOption[i]=new JRadioButton("Option "+(i+1));
            correctOption[i].addActionListener(buttonListener);
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
            System.out.println(correctAnswer);
        }
    };




}


public class addSymbolquestion {

    public static void main(String[] args) {
        new AddQuestion();

    }

}
