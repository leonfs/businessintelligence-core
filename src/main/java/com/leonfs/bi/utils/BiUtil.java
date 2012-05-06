package com.leonfs.bi.utils;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BiUtil {
	
	private static Map<Class<?>, String> databaseTypes = new HashMap<Class<?>, String>();
	private static Properties biConfigurationProperties = null;
	private static ApplicationContext context = null;
	//private static ETLExecutor executor = null;
	
	
	static {	
		/*************** Database Types *********************/
		databaseTypes.put(String.class, "VARCHAR(255)");
		databaseTypes.put(Integer.class, "INTEGER");
		databaseTypes.put(Long.class, "BIGINT(20)");
	}
	
	
	public static String getDatabaseType(Class clazz){
		return databaseTypes.get(clazz);
	}
	
	
	public static String getSpringContextFileConfigurationPath(){
		return "biContext.xml";
	}
	
	public static String getDescriptorsDirectory(){
		//return  "/repository/upload/descriptors";
		return "/home/leonfs/workspace/bi-engine/descriptors/";
	}

	public static ApplicationContext getApplicationContext() {
		if(context == null){
			context = new ClassPathXmlApplicationContext(getSpringContextFileConfigurationPath());
		}
		return context;
	}
	
	/*
	public static ETLExecutor getETLExecutor(){
		if(executor == null){
			executor = new ETLExecutor();
		}
		return executor;
	}*/

	public static String getRepositoryPath() {
		return "/repository/";
	}
	
	public static Map<String, String> deserializeChart(String serializedChart){
		String[] parameters = serializedChart.split(";");
		Map<String, String> parametersMap = new HashMap<String, String>();
		for (int i = 0; i < parameters.length; i++) {
			String[] values = parameters[i].split(":");
			parametersMap.put(values[0], values[1]);
		}
		
		return parametersMap;
	}
	
	public static String typeClassToString(Class clazz){
		if(clazz==null){
			return "";
		}
		String name = clazz.getName();
		int beginIndex = name.lastIndexOf(".")+1;
		int endIndex = name.length();
		name = name.substring(beginIndex, endIndex);
		name = name.toLowerCase();
		return name;
	}
	
	public static Long getActualSeconds() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.getTimeInMillis()/1000;
	}

}
