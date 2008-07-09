package com.sabre.hdt.persistence;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sabre.hdt.rules.impl.TestRule;

public class DAOFactory {
	private static Logger logger = Logger.getLogger(TestRule.class.getName());
	private static Properties daoObjects = null;
	
	public static Object getDAO(String entityClassName){
		logger.debug("Loading DAO object for class " + entityClassName);
		Object daoObject = null;
		if (daoObjects == null) {
			URL url = ClassLoader.getSystemResource("daoObjects.properties");
			try {
				daoObjects = new Properties();
				daoObjects.load(url.openStream());
			} catch (IOException e) {
				logger.error(e);
				throw new RuntimeException("Error while instantiating DAO object implementation for " + entityClassName);
			}
		}
		
		String daoClass = daoObjects.getProperty(entityClassName);
		daoObject = com.sabre.hdt.util.ClassLoader.loadClass(daoClass);
		return daoObject;
	}
}
