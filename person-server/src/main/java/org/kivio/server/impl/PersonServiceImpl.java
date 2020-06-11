package org.kivio.server.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.kivio.api.IPersonService;
import org.kivio.exception.PersonException;
import org.kivio.model.IFamily;
import org.kivio.model.Person;
import org.slf4j.Logger;

@RequestScoped
public class PersonServiceImpl implements IPersonService {
	@Inject
	private IFamily familyInstance;
	
	@Inject
	private Logger log;
	
	public PersonServiceImpl() {
		// for injection
	}
	
	// for testing purposes
	public PersonServiceImpl(IFamily family) {
		this.familyInstance = family;
	}

	@Override
	public Person getById(String id) {
		checkList();

		Optional<Person> person = findById(id);

		if (!person.isPresent()) {
			log.warn("Person with UUID {} not found", id);
			throw new PersonException("Person with UUID " + id + " not found");
		} else {
			return person.get();
		}
	}

	@Override
	public List<Person> getByName(String name) {
		checkList();

		Objects.requireNonNull(name, "No name for filtering");

		return familyInstance.getMembers().stream()
				.filter(fi -> name.equalsIgnoreCase(fi.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Person> listAll() {
		return familyInstance.getMembers();
	}

	@Override
	public void add(Person person) {
		familyInstance.add(person);
	}

	@Override
	public void remove(Person person) {
		familyInstance.remove(person);
	}

	@Override
	public void removeById(String id) {
		checkList();

		Optional<Person> person = findById(id);

		if (person.isPresent()) {
			log.info("Removing person {}", person);
			remove(person.get());
		} else {
			throw new PersonException("Person with UUID " + id + " not found");
		}
	}

	private void checkList() {
		if (familyInstance == null || familyInstance.getMembers().isEmpty()) {
			throw new IllegalArgumentException("Family has no members");
		}
	}

	private Optional<Person> findById(String id) {
		Objects.requireNonNull(id, "No id for filtering");

		return familyInstance.getMembers().stream()
				.filter(fi -> id.equals(fi.getId()))
				.findFirst();
	}
}
