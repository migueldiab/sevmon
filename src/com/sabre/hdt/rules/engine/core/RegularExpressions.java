package com.sabre.hdt.rules.engine.core;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class RegularExpressions {
	
	private static Logger logger = Logger.getLogger(RulesGroup.class.getName());
	
	private List<RE> re;
	
	public RegularExpressions(){
		
		this.re = new ArrayList();
	}
	
	public void loadRegularExpression() {
		
		logger.debug("Loading regular expressions = ");// + getRuleId());
		
	    try {
	        //  Use SAXBuilder
	        SAXBuilder builder = new SAXBuilder();
			URL url = ClassLoader.getSystemResource("re.xml");

	        Document doc = builder.build(url.openStream());
	        Element root = doc.getRootElement();
	        
	        List<Element> childs = root.getChildren("re");
	        	        	
		    for (int i = 0; i < childs.size(); i++) {
		        	Element child = childs.get(i);
					int id = Integer.parseInt(child.getAttributeValue("id"));
					String exp = child.getAttributeValue("exp");
					
					logger.debug("Setting Regular Expression "+ exp);
					
										
					this.re.add(new RE(id,exp));															
			}								        	      	       
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	}
}
