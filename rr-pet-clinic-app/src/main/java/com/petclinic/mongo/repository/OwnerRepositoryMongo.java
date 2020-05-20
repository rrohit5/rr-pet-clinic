package com.petclinic.mongo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.mongo.model.OwnerMongo;

public interface OwnerRepositoryMongo extends CrudRepository<OwnerMongo, String> {
	
	 OwnerMongo findByLastName(String lastName);
	 
	 List<OwnerMongo> findAllByLastNameLike(String lastName);

}
