package com.sushant.verma.common.utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLoader {

	private ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static SpringLoader springLoader=null;
	private static Logger log=LogManager.getLogger(SpringLoader.class);
	private SpringLoader(){
		log.info("@ SpringLoader ");
	}

	public static ApplicationContext getSpringFactory(){
		if (springLoader==null){
			SpringLoader.springLoader=new SpringLoader();
		}
		return SpringLoader.springLoader.factory;
	}
}


//static ApplicationContext factory = SpringLoader.getSpringFactory();
//static MyInterface preObj = (MyInterface)  factory.getBean("MyService_BeanId");
