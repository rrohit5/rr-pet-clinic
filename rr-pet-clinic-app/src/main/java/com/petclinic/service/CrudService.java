package com.petclinic.service;

import java.util.List;

//Spring CRUD repository uses this pattern. mimicking the same.
public interface CrudService <T, ID> {

	List<T> findAll();
	T findById(ID id);
	T save(T object);
	void delete(T object);
	void deleteById(ID id);
}
