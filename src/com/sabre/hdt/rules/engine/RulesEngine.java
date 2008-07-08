package com.sabre.hdt.rules.engine;

import java.util.List;

import com.sabre.hdt.entities.Activity;
import com.sabre.hdt.rules.engine.core.RegularExpressions;
import com.sabre.hdt.rules.engine.core.RulesGroup;

public class RulesEngine {
	
	
	
	
	public void run(){
		
		RegularExpressions res = new RegularExpressions();
		res.loadRegularExpression();
		
		RulesGroup rg = new RulesGroup();
		rg.execute(new Activity());					
		
	}
}
