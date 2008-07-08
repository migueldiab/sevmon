package com.sabre.hdt.parser.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sabre.hdt.entities.Activity;
import com.sabre.hdt.parser.db.DataSource;
import com.sabre.hdt.rules.engine.core.RulesGroup;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = DataSource.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from employee where empFirstName = ?");
			ps.setString(1, "Guillermo");
			if(ps.execute()){
				ResultSet rs = ps.getResultSet();
				while(rs.next()){
					System.out.println(rs.getString("empFirstName"));
					System.out.println(rs.getString("empLastName"));
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RulesGroup rg = new RulesGroup(1);
		rg.execute(new Activity());
		
	}

}
