package com.pathmasri.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pathmasri.messenger.model.Profile;
import com.pathmasri.messenger.service.ProfileService;

@Path("/profiles")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
//@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getProfiles(){
		return profileService.getAllProfiles();
	}
	
	@GET
	@Path("/{profilename}")
	public Profile getProfile(@PathParam("profilename")String profileName){		
		return profileService.getProfile(profileName);
	}
	
	@POST
	public Profile addProfile(Profile profile){
		return profileService.addProfile(profile);
	}
	
	@PUT
	@Path("/{profilename}")
	public Profile updateProfile(@PathParam("profilename")String profileName, Profile profile){
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profilename}")
	public void removeProfile(@PathParam("profilename") String profileName){
		profileService.removeProfile(profileName);
	}
	
	@GET
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}

}
