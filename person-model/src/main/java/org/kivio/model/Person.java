package org.kivio.model;

import java.util.UUID;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Person {
	private String id;
	private String surname;
	private String name;
	
	public Person() {
		// do nothing
	}
	
	public Person(String surname, String name) {
		this.id = UUID.randomUUID().toString();
		this.surname = surname;
		this.name = name;
	}
	
	public String getId() {
		return this.id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(surname)
				.append(name)
				.build();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		
		Person rhs = (Person) obj;
		
		return new EqualsBuilder()
				.append(this.name, rhs.name)
				.append(this.surname, rhs.surname)
				.isEquals();
	}
}
