package com.pathmasri.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pathmasri.messenger.model.ErrorMessage;


public class GenericEceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable arg0) {
		ErrorMessage em = new ErrorMessage(arg0.getMessage(),500,"www.test.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(em)
				.build();
	}

}
