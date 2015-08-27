package com.pathmasri.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentResource {

	@GET
	 public String test(){
		return "test";
	}
	
	@GET
	@Path("/{commentId}")
	public String getComment(@PathParam("messageId") int messageId,@PathParam("commentId") int commentId){
		return "testComment" + messageId;
	}
}
