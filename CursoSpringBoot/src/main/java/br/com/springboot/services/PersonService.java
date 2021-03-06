package br.com.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.springboot.converters.DozerConverter;
import br.com.springboot.data.entity.Person;
import br.com.springboot.data.vo.v1.PersonVO;
import br.com.springboot.exceptions.ResourceNotFoundException;
import br.com.springboot.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	public PersonVO findById(Long id) {
		var entity = repository.findById(id)
						 .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for this ID #%s", id)));
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public Page<PersonVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToPersonVO);
	}
	
	public Page<PersonVO> findByFirstName(String firstName, Pageable pageable) {
		var page = repository.findByFirstName(firstName, pageable);
		return page.map(this::convertToPersonVO);
	}
	
	public PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		entity = repository.save(entity);
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public PersonVO update(PersonVO person) {
		var vo = findById(person.getKey());
		
		var entity = DozerConverter.parseObject(vo, Person.class);
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		entity = repository.save(entity);
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public void delete(Long id) {
		var entity = repository.findById(id)
							   .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for this ID #%s", id)));
		
		repository.delete(entity);
	}
	
	@Transactional
	public void disable(Long id) {
		repository.disable(id);
	}
	
}
