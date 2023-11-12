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

public class test {

    String uri;
    MongoDatabase database;
    MongoCollection<Document> collection;
    public test(){
        uri = "mongodb+srv://Fuzail:Fuzailraza111@cluster0.belxlmj.mongodb.net/?retryWrites=true&w=majority";

        try {
            MongoClient mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("Driving_Center");
            collection = database.getCollection("Persons");



        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "User Login SuccessFully + Conncetion");
        }


    }


    public Document readDocument(int id){
        Document readDoc = collection.find(eq("Ide", id)).first();
        if (readDoc != null) {
            System.out.println("Document found: " + readDoc.toJson());
            System.out.println("Name : - " + readDoc.getString("name"));
            System.out.println("Password : - " + readDoc.getString("Password"));

            return readDoc;
        }
        else {
            System.out.println("No matching documents found.");
        }
        return null;

    }


    public static void main(String[] args) {
        test t1=new test();

        t1.readDocument(39091);
    }

}
