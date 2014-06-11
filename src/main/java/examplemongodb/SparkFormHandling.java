package examplemongodb;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SparkFormHandling {

	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");


		Route sad = new Route("/"){
			@Override
			public Object handle(final Request request,final Response response){
				StringWriter writer = new StringWriter();
				try {
					Map<String, Object> fruitsMap = new HashMap<String, Object>();
					fruitsMap.put("fruits",Arrays.asList("apple","orange","banana","peach"));
					
					Template fruitetemplate = configuration.getTemplate("FruitPicker.ftl");
				
					fruitetemplate.process(fruitsMap,writer);
					System.out.println(writer);

				} catch (IOException |TemplateException e) {
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		};
		Spark.get(sad);
		
		Route form = new Route("/favorite_fruit"){
			@Override
			public Object handle(final Request request, final Response reponse){
				final String fruit = request.queryParams("fruit");
				if(fruit==null){
					return "Why you don't pick one?";
				}else{
					return "Your favorite fruit is: "+fruit+".";
				}
			}
		};
		
		Spark.post(form);
	}

}
