package examplemongodb;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Freemarkerstyle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(Freemarkerstyle.class, "/");
		
		try {
			Template hellotemplate = configuration.getTemplate("hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String, Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name", "Freemarker");
			hellotemplate.process(helloMap,writer);
			
			System.out.println(writer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
	}

}
