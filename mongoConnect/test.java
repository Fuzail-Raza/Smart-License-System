import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.CvType;
import org.opencv.core.CvType.*;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Point;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;

public class test {

    private JFrame frame;
    private JLabel imageLabel;
    private VideoCapture capture;
    private JButton captureButton;

    public test() {
        frame = new JFrame("Camera Capture App");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        imageLabel = new JLabel();
        captureButton = new JButton("Capture");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(captureButton, BorderLayout.SOUTH);

        frame.setLayout(new FlowLayout());
        frame.add(panel);

        initCamera();

        captureButton.addActionListener(e -> captureImage());

        frame.setVisible(true);
    }

    private void initCamera() {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        capture = new VideoCapture(0); // 0 corresponds to the default camera

        if (capture.isOpened()) {
            System.out.println("Camera is opened.");
            Runnable frameGrabber = this::grabFrame;
            new Thread(frameGrabber).start();
        } else {
            System.err.println("Error: Unable to open camera.");
        }
    }

    private void grabFrame() {
        Mat frameMat = new Mat();
        MatOfByte buffer = new MatOfByte();

        while (true) {
            if (capture.read(frameMat)) {
                Imgcodecs.imencode(".png", frameMat, buffer);
                Image image = matToImage(frameMat);

                imageLabel.setIcon(new javax.swing.ImageIcon(image));
                frame.repaint();
            }
        }
    }

    private void captureImage() {
        if (capture.isOpened()) {
            Mat frameMat = new Mat();
            capture.read(frameMat);
            Imgcodecs.imwrite("captured_image.png", frameMat);
            System.out.println("Image captured and saved as captured_image.png");
        }
    }

    private Image matToImage(Mat mat) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", mat, buffer);
        return new ImageIcon(buffer.toArray()).getImage();
    }

    public static void main(String[] args) {
        new test();
    }
}
