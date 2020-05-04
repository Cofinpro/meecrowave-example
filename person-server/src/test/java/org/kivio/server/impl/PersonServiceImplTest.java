package org.kivio.server.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kivio.exception.PersonException;
import org.kivio.model.Person;

public class PersonServiceImplTest {
	private PersonServiceImpl serviceUnderTest;
	
	@BeforeEach
	public void setUp() {
		serviceUnderTest = new PersonServiceImpl(new SimpsonsFamily());
		assertNotNull(serviceUnderTest.listAll());
		assertFalse(serviceUnderTest.listAll().isEmpty());
	}
	
	@Test
	public void itDoesNotGetWithMissingId() {
		Assertions.assertThrows(NullPointerException.class, () -> serviceUnderTest.getById(null));
	}
	
	@Test
	public void itThrowsExceptionWithInvalidId() {
		final String id = UUID.randomUUID().toString();
		
		Assertions.assertThrows(NullPointerException.class, () -> serviceUnderTest.getById(id));
	}
	
	@Test
	public void itGetsWithValidId() throws PersonException {
		final Person expected = serviceUnderTest.listAll().get(2);
		final String id = expected.getId();
		
		final Person actual = serviceUnderTest.getById(id);
		assertTrue(expected == actual);
	}
	
	@Test
	public void itDoesNotGetWithMissingName() {
		Assertions.assertThrows(NullPointerException.class, () -> serviceUnderTest.getByName(null));
	}
	
	@Test
	public void itGetsWithName() {
		final String name = "Simpson";
		final int expectedSize = serviceUnderTest.listAll().size();
		
		List<Person> actual = serviceUnderTest.getByName(name);
		
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertThat(actual.size(), is(expectedSize));
	}
}
