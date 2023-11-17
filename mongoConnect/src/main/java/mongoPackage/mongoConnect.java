package mongoPackage;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.Binary;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.HashMap;
import java.util.Map;

public class mongoConnect{
    String uri;
    MongoDatabase database;
    MongoCollection<Document> collection;
    public mongoConnect(){
        uri = "mongodb+srv://Fuzail:Fuzailraza111@cluster0.belxlmj.mongodb.net/?retryWrites=true&w=majority";

        try {
            MongoClient mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("Driving_Center");
            collection = database.getCollection("testing");



        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "User Login SuccessFully + Conncetion");
        }

    }

    public void createDocument( Map<String, Object> documentMap){
        try {
//            Document newPerson = new Document("Ide", ide)
//                    .append("name", name)
//                    .append("Password", passwd)
//                    .append("city", city)
//                    .append("Image",storeImage(imagePa));
            Document newPerson = new Document(documentMap);
            collection.insertOne(newPerson);
//            JOptionPane.showMessageDialog(null,"Document inserted: " + newPerson.toJson());
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void updateDocument(int id,String name){
        collection.updateOne(eq("Ide", id), set("name", name));
        System.out.println("Document updated.");
    }
    public void deleteDocument(int id){
        collection.deleteOne(eq("Ide", id));
        System.out.println("Document deleted.");
    }
    public Document readDocument(String ide){
        Document readDoc = collection.find(eq("Cnic", "3520101367325")).first();
        if (readDoc != null) {
//            System.out.println("Document found: " + readDoc.toJson());

            return readDoc;
        }
        else {
            System.out.println("No matching documents found.");
        }
        return null;

    }

    public byte[] storeImage(String imageP) throws IOException {
        // Step 1: Convert image to binary data

        Path imagePath = Paths.get(imageP);

        return Files.readAllBytes(imagePath);
        // Step 2: Store image in the database
//        Document imageDocument = new Document("name", "Image1")
//                .append("data", imageBytes);
//        collection.insertOne(imageDocument);
//
//        System.out.println("Image stored in the database.");



    }

    public byte[] fetchImage(Binary retrievedImageBinary ){

        if (retrievedImageBinary != null) {

                byte[] retrievedImageBytes = retrievedImageBinary.getData();
                JFrame frame = new JFrame();
                frame.setTitle(" Added Title");
                JPanel j = new JPanel();
                JLabel l1 = new JLabel("");
                ImageIcon imageIcon = new ImageIcon(retrievedImageBytes);
                l1.setIcon(imageIcon);

                return retrievedImageBytes;
            }
            else {
                System.out.println("Image data is null. Unable to display the image.");
                return null;
            }

    }

    Document searchDocument(){
        return collection.find(eq("name", "Image1")).first();

    }


    public static void main(String[] args) {


        new mongoConnect();


    }
}
