package com.sabre.hdt.persistence.dao;

import com.sabre.hdt.entities.Activity;
import com.sabre.hdt.persistence.dao.exceptions.ActivityNotFoundException;

public interface ActivityDAO {

	public Activity findMovieByPK(String pk) throws ActivityNotFoundException;
	
	public void insertActivity(Activity activity);
	
	public void updateActivity(Activity activity) throws ActivityNotFoundException;
	
	public void deleteMovie(String id) throws ActivityNotFoundException;
}
