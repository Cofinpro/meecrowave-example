package org.kivio.api;

import java.util.List;

import org.kivio.model.Person;

public abstract interface IPersonService {
	Person getById(String id);
	List<Person> getByName(String name);
	List<Person> listAll();
	void add(Person person);
	void remove(Person person);
	void removeById(String id);
}
