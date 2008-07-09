package com.sabre.hdt.rules.impl;

import java.util.Iterator;
import java.util.regex.*;
import com.sabre.hdt.entities.*;
import com.sabre.hdt.parser.test.Test;
import com.sabre.hdt.rules.engine.core.*;
import org.apache.log4j.Logger;


public class Rule001 extends Rule {

	private static Logger logger = Logger.getLogger(Test.class.getName());
	
	public int execute(Activity activity) {
		String body = activity.getDescription();
		String subject = activity.getActivityName();
		
		int tmpScore = 0;	
		boolean b = false;
		
		for (Iterator iterator = this.getRegularExpressions().iterator(); iterator.hasNext();) {
			Integer i = (Integer) iterator.next();
			String regexp = RegularExpressions.getRE(i);
			Pattern pattregex = Pattern.compile(regexp);
			Matcher regexFit = pattregex.matcher(body);	
			Matcher subjectFit = pattregex.matcher(subject);	
			
            if (regexFit.find()) {
            	logger.debug(" INSIDE REGEXP");
            	logger.debug("LIFETIME  :" + activity.getLifeTime() );
            	tmpScore = (int)(tmpScore + this.getValue() + activity.getLifeTime() * 0.05); 
            	b = true;         
            } 
            
            if (subjectFit.find()) {
            	logger.debug(" INSIDE REGEXP");
            	logger.debug("LIFETIME  :" + activity.getLifeTime() );
            	tmpScore = (int)(tmpScore + this.getValue() + activity.getLifeTime() * 0.05);
            	b = true;
            } 
		}
						
		if (b == false)
			if (tmpScore == 0){
				tmpScore = 10;
				tmpScore = (int)(tmpScore + activity.getLifeTime() * 0.05);
			}
		
		logger.debug("RULE SCORE" + tmpScore );
		return tmpScore;		
	}
}