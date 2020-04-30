package org.kivio.api;

import java.util.List;

import org.kivio.exception.PersonException;
import org.kivio.model.Person;

public abstract interface IPersonService {
	Person getById(String id) throws PersonException;
	List<Person> getByName(String name);
	List<Person> listAll();
	void add(Person person) throws PersonException;
	void remove(Person person) throws PersonException;
	void removeById(String id) throws PersonException;
}
