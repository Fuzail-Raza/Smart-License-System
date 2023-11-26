import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    public test() {
        setTitle("Rectangle with Divided Squares");
        setSize(1200, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel drawingPanel = new DrawingPanel();
        getContentPane().add(drawingPanel);

        setVisible(true);
    }

    private class DrawingPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int rectangleWidth = 800;
            int rectangleHeight = 50;

            int squareSize = rectangleWidth / 10;

            int startX = 100;
            int startY = 50;

            // Draw the rectangle
//            g.drawRect(startX, startY, rectangleWidth, rectangleHeight);

//             Draw the divided squares
            for (int i = 0; i < 10; i++) {
                int x = startX + i * squareSize;
                int y = startY;
                g.drawRect(x, y, squareSize, squareSize/2);
                Font f1=new Font("Arial",Font.BOLD,18);
                g.setFont(f1);
                g.drawString("Q"+(i+1),x+28,y+30);
            }
            for (int i = 0; i < 10; i++) {
                int x = startX + i * squareSize;
                int y = startY+40;
                g.drawRect(x, y, squareSize, squareSize/2);
                if (i % 2 == 0) {
                    drawTick(g, x, y, squareSize, squareSize / 2);
                } else {
                    drawCross(g, x, y, squareSize-10, squareSize / 2);
                }
            }
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new test());
    }
}
