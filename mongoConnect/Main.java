import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField inputField;
    private JButton sortButton;
    private JButton nextButton; // New button for next step
    private JLabel resultLabel;
    private int[] numbers;
    private int index = 0;

    public Main() {
        setTitle("Insertion Sort GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new FlowLayout());

        inputField = new JTextField(20);
        add(inputField);

        sortButton = new JButton("Sort Step");
        add(sortButton);

        nextButton = new JButton("Next Sort Step"); // New button
        add(nextButton); // Adding the new button

        resultLabel = new JLabel();
        add(resultLabel);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSortStep();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performNextStep(); // Invokes the next step function
            }
        });

        setVisible(true);
    }

    private void performSortStep() {
        if (numbers == null) {
            String input = inputField.getText();
            String[] inputNumbers = input.split("\\s+");

            numbers = new int[inputNumbers.length];
            for (int i = 0; i < inputNumbers.length; i++) {
                numbers[i] = Integer.parseInt(inputNumbers[i]);
            }

            resultLabel.setText("Entered numbers: " + arrayToString(numbers));
            inputField.setEnabled(false);
            sortButton.setEnabled(false);
        }
    }

    private void performNextStep() {
        if (index < numbers.length) {
            insertionSortStep(numbers, index);
            index++;
            resultLabel.setText("Current state: " + arrayToString(numbers));
        } else {
            resultLabel.setText("Sorted array: " + arrayToString(numbers));
            sortButton.setEnabled(false);
            nextButton.setEnabled(false); // Disables next button after sorting completion
        }
    }

    private void insertionSortStep(int[] arr, int i) {
        int key = arr[i];
        int j = i - 1;

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }

    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new Main();
    }
}