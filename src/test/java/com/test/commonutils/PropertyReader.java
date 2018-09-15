package com.test.commonutils;

import java.io.InputStream;
import java.util.Properties;


public class PropertyReader {
	private Properties properties;

	public PropertyReader(String propertyFilePath) throws Exception,Error {
	
		InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream(propertyFilePath);
		
		properties = new Properties();
		properties.load(input);
	}

	public  String getPropertyValue(String propertyKey) throws Exception,Error {	
		try{
		return properties.getProperty(propertyKey);
		} catch (Exception | Error e) {
			throw e;
		}
		

	}
}
