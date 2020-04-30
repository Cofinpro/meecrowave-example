package org.kivio.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PersonTest {
	@Test
	public void testIfTwoDifferentPersonsAreEqual() {
		Person person1 = new Person("Homer", "Simpson");
		Person person2 = new Person("Homer", "Simpson");
		
		assertEquals(person1, person2);
	}
}
