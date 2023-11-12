package mongoAPI;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bson.types.Binary;
import javax.swing.*;

public class MainTest {
	public static void main(String[] args) {

		// Replace the placeholder with your MongoDB deployment's connection string
		String uri = "mongodb+srv://Fuzail:Fuzailraza111@cluster0.belxlmj.mongodb.net/?retryWrites=true&w=majority";

		try (MongoClient mongoClient = MongoClients.create(uri)) {
			MongoDatabase database = mongoClient.getDatabase("Driving_Center");
			MongoCollection<Document> collection = database.getCollection("Persons");

			// Create
			Document newPerson = new Document("name", "John Doe")
					.append("age", 25)
					.append("city", "New York");
			collection.insertOne(newPerson);
			System.out.println("Document inserted: " + newPerson.toJson());

			// Read
			Document readDoc = collection.find(eq("name", "John Doe")).first();
			if (readDoc != null) {
				System.out.println("Document found: " + readDoc.toJson());
			} else {
				System.out.println("No matching documents found.");
			}

			// Update
			collection.updateOne(eq("name", "John Doe"), set("age", 30));
			System.out.println("Document updated.");

			// Read after update
			Document updatedDoc = collection.find(eq("name", "Raza")).first();
			if (updatedDoc != null) {
				System.out.println("Updated document: " + updatedDoc.toJson());
			} else {
				System.out.println("No matching documents found after update.");
			}


			// Step 1: Convert image to binary data
			Path imagePath = Paths.get("E:\\Programms\\Java\\ACP-Tasks\\JAVA project\\Images\\1675105387954.jpeg");
			byte[] imageBytes = Files.readAllBytes(imagePath);

			// Step 2: Store image in the database
			Document imageDocument = new Document("name", "Image1")
					.append("data", imageBytes);
			collection.insertOne(imageDocument);

			System.out.println("Image stored in the database.");




			Document retrievedDocument = collection.find(eq("name", "Image1")).first();

			if (retrievedDocument != null) {
				// Step 4: Convert binary data to image
				Binary retrievedImageBinary = retrievedDocument.get("data", Binary.class);
				String name=retrievedDocument.get("name", String.class);
				// You can now save the bytes to a new image file or display it as needed
				System.out.println("Image retrieved from the database.");
				if (retrievedImageBinary != null) {
					byte[] retrievedImageBytes = retrievedImageBinary.getData();
					JFrame frame = new JFrame();
					frame.setTitle(name+" Added Title");
					JPanel j = new JPanel();
					JLabel l1 = new JLabel("");
					ImageIcon imageIcon = new ImageIcon(retrievedImageBytes);
					l1.setIcon(imageIcon);
					j.add(l1);
					frame.add(j);
					frame.setSize(400, 400);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else {
					System.out.println("Image data is null. Unable to display the image.");
				}
			} else {
				System.out.println("No image found in the database.");
			}

			// Delete
//			collection.deleteOne(eq("name", "John Doe"));
//			System.out.println("Document deleted.");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
