package org.kivio.model;

import java.util.List;

import org.kivio.exception.PersonException;

public abstract interface IFamily {
	List<Person> getMembers();
	void add(Person person) throws PersonException;
	void remove(Person person) throws PersonException;
}
