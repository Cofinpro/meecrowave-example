package org.kivio.server.endpoints;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.meecrowave.Meecrowave;
import org.apache.meecrowave.junit5.MonoMeecrowaveConfig;
import org.apache.meecrowave.testing.ConfigurationInject;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kivio.model.Person;

@MonoMeecrowaveConfig
public class PersonEndpointTest {
	private WebTarget baseTarget;
	
	@ConfigurationInject
	private Meecrowave.Builder config;
	
	@BeforeEach
	public void setUp() {
		String baseUrl = String.format("http://localhost:%d/person", config.getHttpPort());
		Client client = ClientBuilder.newClient();
		
		baseTarget = client.target(baseUrl);
	}
	
	@Test
	public void itListsAllExisting() {
		List<Person> personList = baseTarget.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Person>>() {});
		
		assertNotNull(personList);
		assertFalse(personList.isEmpty());
	}
	
	@Test
	public void itAddsANewPerson() {
		Person newPerson = new Person("Mona", "Simpson");
		Response response = baseTarget.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(newPerson));
		
		assertNotNull(response);
		assertThat(response.getStatus(), is(201));
	}
	
	@Test
	public void itThrowsExceptionOnAddingDuplicate() {
		Person duplicate = new Person("Homer", "Simpson");
		Response response = baseTarget.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(duplicate));
		
		assertNotNull(response);
		assertThat(response.getStatus(), is(406));
	}
}
