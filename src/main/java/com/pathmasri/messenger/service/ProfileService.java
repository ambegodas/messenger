package com.pathmasri.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pathmasri.messenger.database.Database;
import com.pathmasri.messenger.model.ErrorMessage;
import com.pathmasri.messenger.model.Profile;

public class ProfileService {
	
	private Map<String,Profile> profiles = Database.getProfiles();
	
	public  ProfileService(){
		
		Profile p1 = new Profile(1L, "Profile1", "firstname1","lastName1");
		Profile p2 = new Profile(2L, "Profile2", "firstname2","lastName2");
		profiles.put(p1.getProfileName(), p1);
		profiles.put(p2.getProfileName(), p2);
				
	}
	
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile addProfile(Profile profile){
		profile.setProfileId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		
		if(profiles.containsKey(profile.getProfileName())){
			profiles.put(profile.getProfileName(), profile);
		} else {
			return null;
		}
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);		
	}
	
	public Profile getProfile(String profileName){
		
		if(profiles.get(profileName) == null){
			ErrorMessage em = new ErrorMessage("profile not found",500,"www.test.com");
			Response response= Response.status(Status.INTERNAL_SERVER_ERROR).entity(em)
					.build();
			throw new WebApplicationException(response);
		}
		
		return profiles.get(profileName);
	}

}
