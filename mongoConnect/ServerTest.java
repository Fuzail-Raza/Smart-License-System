import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerTest {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Create ObjectInputStream to read serialized object from the socket
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            // Deserialize the object
            Map<String, Object> documentMap = new HashMap<>();
            documentMap= (Map<String, Object>) objectInputStream.readObject();
            System.out.println("Received student details: " +documentMap);

            // Close resources
            objectInputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
