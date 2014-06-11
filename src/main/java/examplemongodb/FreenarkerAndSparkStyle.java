package examplemongodb;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class FreenarkerAndSparkStyle {

	public static void main(String[]args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(FreenarkerAndSparkStyle.class, "/");


		Route sad = new Route("/"){
			@Override
			public Object handle(final Request request,final Response response){
				StringWriter writer = new StringWriter();
				try {
					Template hellotemplate = configuration.getTemplate("hello.ftl");
					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Freemarker");
					hellotemplate.process(helloMap,writer);
					System.out.println(writer);

				} catch (IOException |TemplateException e) {
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		};
		Spark.get(sad);
	}

}
