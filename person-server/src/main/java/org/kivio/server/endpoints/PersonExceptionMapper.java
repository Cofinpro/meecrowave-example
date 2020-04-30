package org.kivio.server.endpoints;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kivio.exception.PersonException;

@Provider
public class PersonExceptionMapper implements ExceptionMapper<PersonException>{

	@Override
	public Response toResponse(PersonException exception) {
		return Response.status(Status.NOT_ACCEPTABLE).entity(exception.getMessage()).build();
	}
}
