package mongoAPI;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class Main {
	public static void main( String[] args ) {

		// Replace the placeholder with your MongoDB deployment's connection string
		String uri = "mongodb+srv://Fuzail:Fuzailraza111@cluster0.belxlmj.mongodb.net/?retryWrites=true&w=majority";

		try (MongoClient mongoClient = MongoClients.create(uri)) {
			MongoDatabase database = mongoClient.getDatabase("Employee_Login");
			MongoCollection<Document> collection = database.getCollection("Persons");

			Document doc = collection.find(eq("name", "Raza")).first();
			if (doc != null) {
				System.out.println(doc.toJson());
			} else {
				System.out.println("No matching documents found.");
			}
		}
	}
}