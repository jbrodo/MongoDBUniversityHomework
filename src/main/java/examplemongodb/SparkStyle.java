package examplemongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkStyle {
	public static void main(String[]args) {
		Route sad = new Route("/"){
			@Override
			public Object handle(final Request request,final Response response){
				return "Hello world from Spark";
			}
		};
		Spark.get(sad);
	}
}
