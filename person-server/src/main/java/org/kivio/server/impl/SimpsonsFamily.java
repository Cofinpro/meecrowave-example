package org.kivio.server.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.kivio.exception.PersonException;
import org.kivio.model.IFamily;
import org.kivio.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class SimpsonsFamily implements IFamily {
	private static final Logger LOG = LoggerFactory.getLogger(SimpsonsFamily.class);
	
	private static final List<Person> MEMBER_LIST = new ArrayList<Person>() {
		private static final long serialVersionUID = 1L;

		{
			add(new Person("Homer", "Simpson"));
			add(new Person("Marge", "Simpson"));
			add(new Person("Bart", "Simpson"));
			add(new Person("Lisa", "Simpson"));
			add(new Person("Maggie", "Simpson"));
		}
	};	
	

	@Override
	public List<Person> getMembers() {
		LOG.debug("listing {} members", MEMBER_LIST.size());
		return MEMBER_LIST;
	}


	@Override
	public void add(Person person) throws PersonException {
		if (MEMBER_LIST.contains(person)) {
			LOG.debug("Person {} still exists in list", person);
			throw new PersonException("Person still exists");
		} else {
			LOG.debug("adding new person to list");
			MEMBER_LIST.add(person);
		}
	}


	@Override
	public void remove(Person person) throws PersonException {
		if (!MEMBER_LIST.contains(person)) {
			LOG.debug("person not found in list");
			throw new PersonException("Person to remove not found");
		} else {
			MEMBER_LIST.remove(person);
		}
	}
}
