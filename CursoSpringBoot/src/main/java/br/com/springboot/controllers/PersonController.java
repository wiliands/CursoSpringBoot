package br.com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.data.vo.PersonVO;
import br.com.springboot.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonService personService;

	@GetMapping("/{id}")
	public PersonVO findById(@PathVariable("id") Long id) {
		return personService.findById(id);
	}

	@GetMapping
	public List<PersonVO> findAll() {
		return personService.findAll();
	}
	
	@PostMapping
	public PersonVO create(@RequestBody PersonVO person) {
		return personService.create(person);
	}
	
	@PutMapping
	public PersonVO update(@RequestBody PersonVO person) {
		return personService.update(person);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		personService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
