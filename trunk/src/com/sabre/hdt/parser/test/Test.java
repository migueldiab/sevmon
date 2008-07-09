package com.sabre.hdt.parser.test;

import java.util.Collection;
import java.util.Iterator;

import com.sabre.hdt.entities.Activity;
import com.sabre.hdt.rules.engine.core.RegularExpressions;
import com.sabre.hdt.rules.engine.core.RulesGroup;
import org.apache.log4j.Logger;
import com.sabre.hdt.rules.engine.core.*;

public class Test {

	private static Logger logger = Logger.getLogger(Test.class.getName());
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Connection conn = DataSource.getConnection();
	
		
		//RegularExpressions res = new RegularExpressions();
		RegularExpressions.loadRegularExpression();

		RulesGroup rg = new RulesGroup();
		Collection<Activity> activityCollection = Activity.findAll();
		for (Iterator iterator = activityCollection.iterator(); iterator
				.hasNext();) {
			Activity activity = (Activity) iterator.next();
			
			logger.debug(" message from main");
			logger.debug(activity);
			
			activity.setScore(rg.execute(activity));
			//activity.update();
		}

	}

}
