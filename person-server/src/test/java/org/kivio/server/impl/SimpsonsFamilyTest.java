package org.kivio.server.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kivio.exception.PersonException;
import org.kivio.model.Person;

public class SimpsonsFamilyTest {
	private SimpsonsFamily familyUnderTest;
	
	@BeforeEach
	public void setUp() {
		familyUnderTest = new SimpsonsFamily();
	}
	
	@Test
	@DisplayName("initial list should not be empty")
	public void itReturnsNonEmptyList() {
		assertFalse(familyUnderTest.getMembers().isEmpty());
		assertTrue(familyUnderTest.getMembers().size() >= 4);
	}
	
	@Test
	@DisplayName("new family members can be added")
	public void itCanAddANonExistingPerson() throws PersonException {
		final int sizeBefore = familyUnderTest.getMembers().size();
		
		familyUnderTest.add(new Person("Abe", "Simpson"));
		
		assertThat(familyUnderTest.getMembers().size(), is(sizeBefore + 1));
	}
	
	@Test
	@DisplayName("duplicates with same name combination are not added")
	public void itDoesNotAddDuplicates() {
		final Person person = new Person("Homer", "Simpson"); // it belongs to initial list
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> familyUnderTest.add(person));	
	}
	
	@Test
	@DisplayName("existing persons with same name combination are removed")
	public void itRemovesExistingPersons() throws PersonException {
		final int sizeBefore = familyUnderTest.getMembers().size();
		final Person personToRemove = new Person("Maggie", "Simpson");
		
		familyUnderTest.remove(personToRemove);
		
		assertThat(familyUnderTest.getMembers().size(), is(sizeBefore - 1));
	}
	
	@Test
	@DisplayName("non existing persons in list are not removed")
	public void itThrowsExceptionWhileRemovingNonExistent() {
		final Person person = new Person("Ned", "Flanders");
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> familyUnderTest.remove(person));	
	}
}
