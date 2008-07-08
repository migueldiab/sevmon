package com.sabre.hdt.util;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.log4j.Logger;

public class ClassLoader {
		
		private static Logger logger = Logger.getLogger(ClassLoader.class.getName());
	
		/**
		 * Load the class identified with the key from properties file with the default constructor.
		 * @param classKey
		 * @return
		 */
		public static Object loadClass(String className) {
			logger.debug("Loading class " + className);
			return createObject(className);
		}

		/**
		 * Load the class identified with the key from properties file with the arcuments passed in 
		 * the arguments map. The method is not completely implemented so it must be completed before used.
		 * @param classKey
		 * @param arguments
		 */
		public static void loadClass(String className, Map<String, String> arguments) {
			Class classDefinition;
			//defino los tipos de los argumentos que va a recibir el constructor de
			// la clase cargada
			Class[] argumentsClass = new Class[] { String.class };
			String name = "PEPE CARGADO COMO ARGUMENTO DEL CONSTRUCTOR";
			//cargo los valores de los argumentos en un array de object
			Object[] argumentValues = new Object[] { name };
			//instancio el constructor
			Constructor constructor;

			try {
				//cargo la definición de la clase
				classDefinition = Class.forName(className);
				//obtengo el constructor de la clase y le paso el tipo de los
				//argumentos que le voy a cargar
				constructor = classDefinition.getConstructor(argumentsClass);
				//instancio la clase
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage(),e);
			} catch (NoSuchMethodException e) {
				logger.error(e.getMessage(),e);
			}
		}

		private static Object createObject(String className) {
			Object object = null;
			try {
				logger.debug("Loading class definition...");
				Class classDefinition = Class.forName(className);
				logger.debug("Getting class instance...");
				object = classDefinition.newInstance();
			} catch (InstantiationException e) {
				logger.error(e.getMessage(),e);
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage(),e);
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage(),e);
			}
			return object;
		}

		public static Object createObject(Constructor constructor,Object[] arguments) {

			Object object = null;

			try {
				//creo la instancia de la clase especificada con los argumentos especificados
				object = constructor.newInstance(arguments);
				return object;
			} catch (InstantiationException e) {
				logger.error(e.getMessage(),e);
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage(),e);
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage(),e);
			} catch (InvocationTargetException e) {
				logger.error(e.getMessage(),e);
			}
			return object;
		}
}
