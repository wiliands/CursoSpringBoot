package br.com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.Person;
import br.com.springboot.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonService personService;

	@RequestMapping(value = "/{id}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable("id") String id) {
		return personService.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() {
		return personService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person create(@RequestBody Person person) {
		return personService.create(person);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person update(@RequestBody Person person) {
		return personService.update(person);
	}
	

	@RequestMapping(value = "/{id}",
					method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		personService.delete(id);
	}
	
}
