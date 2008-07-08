package com.sabre.hdt.entities;

import java.util.Date;

public class Activity {

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
	
	public Activity(String activityId, String activityName, String status, String attachments, String emailCC, String accountLocation,
			String description, String emailSender, Date lastUpdated, Date plannedStart, Date created){
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

}
