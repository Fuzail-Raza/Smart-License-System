import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    private JLabel dateOfBirthLabel;
    private JDateChooser dateOfBirthChooser;

    public test() {
        JFrame frame = new JFrame("Date of Birth Chooser Example");
        JPanel panel = new JPanel();

        // Create a JLabel for "Date of Birth"
        dateOfBirthLabel = new JLabel("Date of Birth");
        panel.add(dateOfBirthLabel);

        // Create a JDateChooser
        dateOfBirthChooser = new JDateChooser();
        panel.add(dateOfBirthChooser);

        // Set initial date (optional)
        dateOfBirthChooser.setDate(new Date());

        // Add a change listener to the JDateChooser
        dateOfBirthChooser.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                handleSelectedDate();
            }
        });

        // Add the panel to the frame
        frame.getContentPane().add(panel);

        // Set frame properties
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void handleSelectedDate() {
        Date selectedDate = dateOfBirthChooser.getDate();
        Date currentDate = new Date();

        if (selectedDate != null && selectedDate.after(currentDate)) {
            // The selected date is in the future
            JOptionPane.showMessageDialog(
                    null,
                    "Please choose a date that is not in the future.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE);

            // Reset the date to the current date
            dateOfBirthChooser.setDate(currentDate);
        } else {
            // Print or use the selected date
            printSelectedDate();
        }
    }

    private void printSelectedDate() {
        Date selectedDate = dateOfBirthChooser.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(selectedDate);
        System.out.println("Selected Date: " + formattedDate);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new test());
    }
}
