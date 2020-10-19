package mecs.camel.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {

	public static Object getBean(String beanName) {
		String str = "applicationContext.xml";
		ApplicationContext conf = new ClassPathXmlApplicationContext(str);
		Object bean = conf.getBean(beanName);
		return bean;
	}
	
}
