package com.sabre.hdt.persistence.dao;

import java.util.Collection;

import com.sabre.hdt.entities.Activity;
import com.sabre.hdt.persistence.dao.exceptions.ActivityNotFoundException;

public interface ActivityDAO {

	public Activity findActivitieByPK(String pk) throws ActivityNotFoundException;
	
	public Collection<Activity> findAll() throws ActivityNotFoundException;
	
	public void insertActivity(Activity activity);
	
	public void updateActivity(Activity activity) throws ActivityNotFoundException;
	
	public void deleteActivity(String pk) throws ActivityNotFoundException;
	
}
