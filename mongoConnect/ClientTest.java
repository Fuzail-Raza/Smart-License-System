import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientTest {

    public static void main(String[] args) {

        try {
            // Create a Student object
            Map<String, Object> documentMap = new HashMap<>();
            documentMap.put("Name", "Fuzail");
            documentMap.put("ID", 123);

            // Create a Socket and connect to the server
            Socket socket = new Socket("localhost", 8080);
            System.out.println("Connected to server.");

            // Create ObjectOutputStream to send serialized object to the server
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            // Serialize and send the object
            objectOutputStream.writeObject(documentMap);
            System.out.println("Sent student details: " + documentMap);

            // Close resources
            objectOutputStream.close();
            socket.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
