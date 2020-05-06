package com.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
	
	 Owner findByLastName(String lastName);
	 
	 List<Owner> findAllByLastNameLike(String lastName);

}
