package com.sabre.hdt.rules.engine.core;

import com.sabre.hdt.entities.Activity;
import java.util.List;
import java.util.*;

public abstract class Rule {
	
	private int value;
	List<Integer> res;
	private int id;
	
	public int execute(Activity activity){		
		return 0;
	}
	
	public void setValue(int val){		
		value = val;
	}
	
	public void setRE(int reID){		
		if (res == null)
			res = new ArrayList();
		res.add(reID);		
	}
	
	public int getValue(){
		return value;
	}
	
	public void setID(int ident){		
		id=ident;		
	}
	
	public int getID(){
		return id;
	}
	
	public List<Integer> getRegularExpressions(){
		return res;
	}		
}


