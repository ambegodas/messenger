package com.pathmasri.messenger.model;

import java.util.Date;

public class Profile {
	
	private long profileId;
	private String profileName;
	private String firstName;
	private String lastName;
	private Date created;
	
	
	
	public Profile(long id, String profileName, String firstName,
			String lastName) {
		this.profileId = id;
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	
	public Profile() {
	}



	public long getProfileId() {
		return profileId;
	}
	public void setProfileId(long id) {
		this.profileId = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	
	

}
