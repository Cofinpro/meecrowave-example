package org.kivio.server.endpoints;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.kivio.api.IPersonService;
import org.kivio.exception.PersonException;
import org.kivio.model.Person;

@RequestScoped
@Path("/person")
public class PersonEndpoint {
	@Inject
	private IPersonService personService;
	
	@POST
	public Response createPerson(Person person) {
		personService.add(person);
		return Response.status(Status.CREATED).entity(person).build();
	}
	
	@DELETE
	public Response removePerson(Person person) throws PersonException {
		personService.remove(person);
		return Response.ok(person).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeById(@PathParam("id") String id) throws PersonException {
		personService.removeById(id);
		return Response.ok().build();
	}
	
	@GET
	public Response listAll() {
		return Response.ok(personService.listAll()).build();
	}
	
	@GET
	@Path("/name")
	public Response getByName(@QueryParam("name") String name) {
		List<Person> personList = personService.getByName(name);
		return Response.ok(personList).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") String id) throws PersonException {
		Person person = personService.getById(id);
		return Response.ok(person).build();
	}
}
