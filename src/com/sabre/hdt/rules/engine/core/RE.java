package com.sabre.hdt.rules.engine.core;

public class RE {
	
	private int id;
	private String re;

	public RE(int id, String re){
		
		this.id = id;
		this.re = re;
	}
	
	public String getRE(){
	
		return this.re;
	}
	
	public int getId(){
		
		return this.id;
	}

}
