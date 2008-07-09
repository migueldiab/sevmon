package com.sabre.hdt.rules.impl;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.sabre.hdt.entities.Activity;
import com.sabre.hdt.parser.test.Test;
import com.sabre.hdt.rules.engine.core.RegularExpressions;
import com.sabre.hdt.rules.engine.core.Rule;

public class REOverSubject extends Rule {

private static Logger logger = Logger.getLogger(TestRule.class.getName());


public int execute(Activity activity) {
	String actName = activity.getActivityName();
	int tmpScore = 0;		
	
	for (Iterator iterator = this.getRegularExpressions().iterator(); iterator.hasNext();) {
		Integer i = (Integer) iterator.next();
		String regexp = RegularExpressions.getRE(i);
		Pattern pattregex = Pattern.compile(regexp);
		Matcher regexFit = pattregex.matcher(actName);	
		
        if (regexFit.find()) {
        	logger.debug(" INSIDE REGEXP");
        	logger.debug("LIFETIME  :" + activity.getLifeTime() );
        	tmpScore = (int)(tmpScore + this.getValue() + activity.getLifeTime() * 0.05); 
        	
        } 
	}
	logger.debug("RULE SCORE" + tmpScore );
	return tmpScore;		
}
}
