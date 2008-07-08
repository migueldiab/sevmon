package com.sabre.hdt.rules.impl;

import org.apache.log4j.Logger;

import com.sabre.hdt.entities.Activity;
import com.sabre.hdt.rules.engine.core.Rule;

public class REOverSubject extends Rule {

private static Logger logger = Logger.getLogger(TestRule.class.getName());
	
	@Override
	public double execute(Activity activity) {
						
		// TODO Auto-generated method stub
		logger.debug("Executing rule...");
		return 0;		
	}
}
