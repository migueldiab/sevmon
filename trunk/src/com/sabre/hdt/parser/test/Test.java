package com.sabre.hdt.parser.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sabre.hdt.entities.Activity;
//import com.sabre.hdt.persistence.DataSource;
import com.sabre.hdt.rules.engine.core.RegularExpressions;
import com.sabre.hdt.rules.engine.core.RulesGroup;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
					
		//Connection conn = DataSource.getConnection();
				
		RegularExpressions res = new RegularExpressions();
		res.loadRegularExpression();
		
		RulesGroup rg = new RulesGroup();
		rg.execute(new Activity());		
	}

}
