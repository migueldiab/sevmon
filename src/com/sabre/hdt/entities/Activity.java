package com.sabre.hdt.entities;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import com.sabre.hdt.persistence.DAOFactory;
import com.sabre.hdt.persistence.PersistentObject;
import com.sabre.hdt.persistence.dao.ActivityDAO;
import com.sabre.hdt.persistence.dao.exceptions.ActivityNotFoundException;

public class Activity extends PersistentObject{
	private static Logger logger = Logger.getLogger(Activity.class.getName());

	private String activityId;
	private String activityName;
	private String status;
	private String attachments;
	private String emailCC;
	private String accountLocation;
	private String description;
	private String emailSender;
	private Date lastUpdated;
	private Date plannedStart;
	private Date created;
	private int score;


	private ActivityDAO dao;
	
	public Activity(String activityId, String activityName, String status, String attachments, String emailCC, String accountLocation,
			String description, String emailSender, Date lastUpdated, Date plannedStart, Date created, int score){
		this.activityId = activityId;
		this.activityName = activityName;
		this.status = status;
		this.attachments = attachments;
		this.emailCC = emailCC;
		this.accountLocation = accountLocation;
		this.description = description;
		this.emailSender = emailSender;
		this.lastUpdated = lastUpdated;
		this.plannedStart = plannedStart;
		this.created = created;
		this.score = score;
	}

	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public Activity(){
		
	}

	public String getActivityId() {
		return activityId;
	}


	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}


	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public String getEmailCC() {
		return emailCC;
	}

	public void setEmailCC(String emailCC) {
		this.emailCC = emailCC;
	}

	public String getAccountLocation() {
		return accountLocation;
	}

	public void setAccountLocation(String accountLocation) {
		this.accountLocation = accountLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getPlannedStart() {
		return plannedStart;
	}

	public void setPlannedStart(Date plannedStart) {
		this.plannedStart = plannedStart;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public ActivityDAO getDao() {
		if(this.dao == null){
			this.dao = (ActivityDAO)DAOFactory.getDAO(Activity.class.getName());
		}
		return dao;
	}

	public void setDao(ActivityDAO dao) {
		this.dao = dao;
	}

	public static ActivityDAO getDaoStatic(){
		return (ActivityDAO)DAOFactory.getDAO(Activity.class.getName());
	} 
	
	@Override
	public String toString() {
		return this.activityId + " " + 
		this.activityName + " " + 
		this.status + " " + 
		this.attachments + " " + 
		this.emailCC + " " + 
		this.accountLocation + " " + 
		this.description + " " + 
		this.emailSender + " " + 
		this.lastUpdated + " " + 
		this.plannedStart + " " + 
		this.created + " ";
	}

	@Override
	public void delete() {
		try {
			getDao().deleteActivity(this.getActivityId());
		} catch (ActivityNotFoundException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert() {
		getDao().insertActivity(this);
	}

	@Override
	public void update() {
		try {
			getDao().updateActivity(this);
		} catch (ActivityNotFoundException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

	public static Activity findByPK(String activityId) throws ActivityNotFoundException {
		return getDaoStatic().findActivityByPK(activityId);
	}

	public static Collection<Activity> findAll() {
		Collection<Activity> activities = null;
		try {
			activities = getDaoStatic().findAll();
		} catch (ActivityNotFoundException e) {
			logger.info("No activities where found...");
		}
		return activities;
	}
	
	public int getLifeTime(){
		
		Date tmpDate = new Date(this.getPlannedStart().getTime());
		Date now = new Date();  
		
		logger.debug("TIME NOW" + now);
		logger.debug("TIME BASE" + tmpDate);
			
		TimeSpan ts = TimeSpan.subtract(now,tmpDate);
				    
		logger.debug("TS " + ts.getSeconds());
		
		return (int) ts.getSeconds(); 
		
		
		//TimeSpan 
		
		//tmpDate.getTime();
		//this.getPlannedStart() 
		
	}	
}
