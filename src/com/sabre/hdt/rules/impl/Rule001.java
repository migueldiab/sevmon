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
		int tmpScore = 0;		
		
		for (Iterator iterator = this.getRegularExpressions().iterator(); iterator.hasNext();) {
			Integer i = (Integer) iterator.next();
			String regexp = RegularExpressions.getRE(i);
			Pattern pattregex = Pattern.compile(regexp);
			Matcher regexFit = pattregex.matcher(body);	
			
            if (regexFit.find()) {
            	logger.debug(" INSIDE REGEXP");
            	tmpScore = tmpScore + this.getValue() + 10;     
            } 
		}
		
		return tmpScore;		
	}
}