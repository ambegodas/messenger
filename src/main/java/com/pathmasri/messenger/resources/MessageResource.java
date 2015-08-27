package com.pathmasri.messenger.resources;


import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.pathmasri.messenger.exception.DataNotFoundException;
import com.pathmasri.messenger.model.Message;
import com.pathmasri.messenger.resources.bean.MessageFilterBean;
import com.pathmasri.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	MessageService service = new MessageService();
	
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
    	if(filterBean.getYear()>0){
    	  return service.getAllMessagesForYear(filterBean.getYear());
    	}
    	if (filterBean.getStart() >=0 && filterBean.getSize()>=0){
    		return service.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
    	}
		return service.getAllMessages();
	}
    
/*    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start, @QueryParam("size") int size
){
    	if(year>0){
    	  return service.getAllMessagesForYear(year);
    	}
    	if (start >=0 && size>=0){
    		return service.getAllMessagesPaginated(start, size);
    	}
		return service.getAllMessages();
	}*/
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId")long messageId, @Context UriInfo uriInfo){
    	if(service.getMessage(messageId) != null){
    		
    		Message message = service.getMessage(messageId);
    		
    		//message.addLink(uriInfo.getAbsolutePath().toString(), "rel");
    		message.addLink(getSelfUri(messageId, uriInfo), "self");
    		message.addLink(getProfileUri(message, uriInfo), "profile");
    		message.addLink(getCommentsUri(message, uriInfo), "comments");
    		return message;
    	} else {
    		throw new DataNotFoundException("MessageNotFound");
    	}
    	
    }

	private String getCommentsUri(Message message, UriInfo uriInfo) {
		
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getMessageId())
				.build().toString();
		return uri;
	}

	private String getProfileUri(Message message, UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class)
				.path(message.getAuthor())
				.build().toString();
		return uri;
}

	private String getSelfUri(long messageId, UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class)
				.path(Long.toString(messageId))
				.build().toString();
		return uri;
	}
   

/*    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message addMessage(Message message){
    	return service.addMessage(message);
    }*/
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
    	
    	Message newMessage = service.addMessage(message);
    	String newId = String.valueOf(message.getMessageId());
    	URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
    	//return Response.status(Status.CREATED).entity(newMessage).build();
    	return Response.created(uri).entity(newMessage).build();
    
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId")long messageId, Message message){
    	 message.setMessageId(messageId);
    	return service.updateMessage(message);
    }
    
    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId")long messageId){
    	service.removeMessage(messageId);
    }
    

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
    	return new CommentResource();
    }
    
}
