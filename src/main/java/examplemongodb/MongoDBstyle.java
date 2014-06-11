package examplemongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoDBstyle {

	public static void main(String[] args) throws UnknownHostException{
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
		
		DB database = client.getDB("test");
		DBCollection collection = database.getCollection("things");
		
		DBObject document = collection.findOne();
		System.out.println(document);
	}
}
