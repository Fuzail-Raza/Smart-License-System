import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("HTML Background Example");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a custom JPanel with HTML content
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            // HTML content with background image
            String htmlContent = "<html><body style='background: url(\"file:symbolImages/bg1.jpg\") no-repeat center center fixed;'>" +
                    "<h1>Hello, HTML Background!</h1>" +
                    "</body></html>";

            JEditorPane editorPane = new JEditorPane("text/html", htmlContent);
            editorPane.setEditable(false);

            // Add the editor pane to the panel
            panel.add(editorPane, BorderLayout.CENTER);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
