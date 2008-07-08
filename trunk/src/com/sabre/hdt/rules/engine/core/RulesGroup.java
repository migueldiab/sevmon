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

	private int ruleId;
	private List<Rule> rules;
	private static Map<String, Element> ruleGroupElements;
	
	public RulesGroup() {
		// TODO Auto-generated constructor stub
	}

	public RulesGroup(int ruleId) {
		logger.debug("Creating rules group with ruleid = " + ruleId);
		this.ruleId = ruleId;
		this.rules = new ArrayList<Rule>();
		this.loadRules();
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public double execute(Activity activity){
		double output = 0;
		for (int i = 0; i < this.rules.size(); i++) {
			Rule rule = this.rules.get(i);
			output += rule.execute(activity);
		}
		return output;
	}
	
	private void loadRules() {
		logger.debug("Loading rules for rule's group id = " + getRuleId());
	    try {
	        //  Use SAXBuilder
	        SAXBuilder builder = new SAXBuilder();
			URL url = ClassLoader.getSystemResource("rules.xml");

	        Document doc = builder.build(url.openStream());
	        Element root = doc.getRootElement();
	        
	        List<Element> childs = root.getChildren("rulesGroup");

	        if (ruleGroupElements == null) {
	        	ruleGroupElements = new HashMap<String, Element>();
		        for (int i = 0; i < childs.size(); i++) {
		        	Element child = childs.get(i);
					String ruleID = child.getAttributeValue("id");
					ruleGroupElements.put(ruleID, child);
				}				
			}
	        Element el = ruleGroupElements.get(String.valueOf(getRuleId()));
	        List<Element> ruleElements = el.getChildren();

	        for (int i = 0; i < ruleElements.size(); i++) {
				Element rule = ruleElements.get(i);
				this.rules.add((Rule)com.sabre.hdt.util.ClassLoader.loadClass(rule.getAttributeValue("class")));
			}

	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }

	}
}
