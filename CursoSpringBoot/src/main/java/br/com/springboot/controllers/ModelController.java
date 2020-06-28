package br.com.springboot.controllers;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;

import br.com.springboot.data.vo.v1.ModelVO;

public interface ModelController<T extends ModelVO<ID>, ID> {
	
	public T findById(ID id);

	public List<T> findAll();
	
	public T create(T t);
	
	public T update(T t);

	public ResponseEntity<?> delete(ID id);
	
	public Link buildLink(T t);

}