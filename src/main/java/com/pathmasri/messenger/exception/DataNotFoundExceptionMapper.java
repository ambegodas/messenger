package com.pathmasri.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pathmasri.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>  {

	@Override
	public Response toResponse(DataNotFoundException dnfe) {
		// TODO Auto-generated method stub
		ErrorMessage em = new ErrorMessage(dnfe.getMessage(),404,"www.test.com");
		return Response.status(Status.NOT_FOUND).entity(em)
				.build();
	}
	
	//Provider annotation will register this as an exception mapper in JAX-RS

}
