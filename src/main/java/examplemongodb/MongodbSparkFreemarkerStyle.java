package examplemongodb;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MongodbSparkFreemarkerStyle {


	public static void main(String[]args) throws UnknownHostException{
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(MongodbSparkFreemarkerStyle.class, "/");

		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));

		DB database = client.getDB("test");
		final DBCollection collection = database.getCollection("otherthings");

		Route sad = new Route("/"){
			@Override
			public Object handle(final Request request,final Response response){
				StringWriter writer = new StringWriter();
				try {
					Template hellotemplate = configuration.getTemplate("hello.ftl");

					DBObject document = collection.findOne();//nel documento ci deve essere un campo "nome"
					hellotemplate.process(document,writer);
					System.out.println(writer);

				} catch (IOException |TemplateException e) {
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		};
		Spark.get(sad);
		
		Route echo = new Route("/echo/:things"){
			@Override
			public Object handle(final Request request,final Response response){
				return request.params(":things");
			}
		};
		Spark.get(echo);
	}
}
