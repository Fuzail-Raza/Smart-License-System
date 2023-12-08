
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.print.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

class test{
    JFrame mainFrame;
    JPanel mainPanel;
    JButton print;
    public test(){
//        mainFrame=new JFrame();
//        mainPanel=new JPanel();
//        print=new JButton("Print");
//
//        mainPanel.add(print);
//        mainFrame.add(mainPanel);
//        mainFrame.setVisible(true);
        printDocument();
//        print.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Print Call");
//                printDocument();
//            }
//        });
    }
    private void printDocument() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = pageFormat.getPaper();
        paper.setSize(1011.0 / 72.0, 639.0 / 72.0); // 1 inch = 72 points
        pageFormat.setPaper(paper);

        printerJob.setPrintable(new MyPrintable(), pageFormat);

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
            g.drawString("Learner Form", 200, 50);

            Stroke originalStroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(3));


            g2d.setStroke(originalStroke);

            g.setFont(originalFont);
            // Info Panel content
            String nameToPrint = "nameInput.getText()".length() > 15 ? "nameInput.getText()".substring(0, 15) : "nameInput.getText()";
            g.drawString("nameInput.getText()", 100, 100);
            Icon icon = null;
            try {
                icon = imageReturn();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (icon instanceof ImageIcon) {
                Image image = ((ImageIcon) icon).getImage();
                int imageWidth = 1011;
                int imageHeight = 639;
                // Draw the image at coordinates (150, 160)
                g.drawImage(image, 0, 0, imageWidth, imageHeight, this);
            } else {
                // Handle the case when the icon is not an ImageIcon
                g.drawString("No image available", 480, 50);
            }
            g.drawString("3520101367325", 300, 100);
            // Add other fields from the info panel as needed

            g.drawString("25-12-2002", 100, 250);
            g.drawString( "25-12-2002", 300, 250);


            int x = 50;
            int y = 290;
            int width = 500;
            int height = 85;


            g2d.setStroke(new BasicStroke(3));

            g2d.drawRect(x, y, width, height);



            return Printable.PAGE_EXISTS;
        }

        private Icon imageReturn() throws IOException {
            Path imagePath = Paths.get("E:\\Programms\\Java\\ACP-Tasks\\mongo\\mongoConnect\\symbolImages\\License print.png");

            byte[] imageBytes=Files.readAllBytes(imagePath);
            return new ImageIcon(imageBytes);
        }

        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            return false;
        }
    }

    public static void main(String[] args) {
        new test();
    }
}