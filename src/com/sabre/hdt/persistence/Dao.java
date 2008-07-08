package com.sabre.hdt.persistence;

public interface Dao {

	public void insert();
	public void update();
	public void delete();
	public void findByPK(String pk);
}
