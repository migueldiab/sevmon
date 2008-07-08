package com.sabre.hdt.rules.impl;

import java.util.regex.*;
import com.sabre.hdt.entities.*;
import com.sabre.hdt.rules.engine.core.*;

public class Rule001 extends Rule {

	public int execute(Activity activity) {
		String body = activity.getDescription();
		Pattern regex = Pattern.compile("INAL.JNEXTDAY");		                		           		                		              
        		Matcher regexFit = regex.matcher(body);		                        
                if (regexFit.matches()) {
                	return this.getValue() + 10;     
                } else {
                        return 0;
                }
	}
}