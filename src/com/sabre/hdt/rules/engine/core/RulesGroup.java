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

import com.sabre.hdt.entities.Activity;

public class RulesGroup {
	private static Logger logger = Logger.getLogger(RulesGroup.class.getName());

	private List<Rule> rules;
	private static Map<String, Element> ruleGroupElements;
		

	public RulesGroup() {
		logger.debug("Creating rules...");
		this.rules = new ArrayList<Rule>();
		this.loadRules();
	}	

	public int execute(Activity activity){
		double output = 0;
		for (int i = 0; i < this.rules.size(); i++) {
			
			logger.debug(" message from EXECUTE");
			logger.debug(activity);
			
			Rule rule = this.rules.get(i);
			output += rule.execute(activity);
			
			logger.debug("OUT from EXECUTE ITERATION");
			logger.debug(output);
		}
		return (int) output;
	}
	
	private void loadRules() {
		logger.debug("Loading rules...");
	    try {
	        //  Use SAXBuilder
	        SAXBuilder builder = new SAXBuilder();
			URL url = ClassLoader.getSystemResource("rules.xml");

	        Document doc = builder.build(url.openStream());
	        Element root = doc.getRootElement();
	        
	        List<Element> childs = root.getChildren("rule");
	        	        	       
		        for (int i = 0; i < childs.size(); i++) {
		        	Element child = childs.get(i);
					//String ruleID = child.getAttributeValue("class");
					
					Rule rl = (Rule)com.sabre.hdt.util.ClassLoader.loadClass(child.getAttributeValue("class"));					
					rl.setValue(Integer.parseInt(child.getAttributeValue("value")));
					rl.setID(Integer.parseInt(child.getAttributeValue("id")));
					logger.debug("Setting Rule "+child.getAttributeValue("class")+ "value = " + rl.getValue());
					
					List<Element> ruleElements = child.getChildren();
					
					for (int j = 0; j < ruleElements.size(); j++) {
						
			        	Element ch = ruleElements.get(j);
						String ruleID = ch.getAttributeValue("id");
						rl.setRE(Integer.parseInt(ruleID));
						logger.debug("Setting Rule RE id = " + ruleID);						
					}
					
					this.rules.add(rl);
				}						
		        
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }	      
	}
}
