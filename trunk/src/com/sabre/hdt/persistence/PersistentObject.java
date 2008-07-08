package com.sabre.hdt.persistence;

public abstract class PersistentObject {
	public abstract void insert();
	public abstract void update();
	public abstract void delete();
}
