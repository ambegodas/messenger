package com.pathmasri.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("/inject")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String param,
			                                @HeaderParam("headerparam") String header,
			                                @CookieParam("cookieparam") String cookie){
		return param + " " + header + " " + cookie;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uri, @Context HttpHeaders header){
		
		return uri.getAbsolutePath().toString() + header.getCookies().toString();
		
	}
	

}
