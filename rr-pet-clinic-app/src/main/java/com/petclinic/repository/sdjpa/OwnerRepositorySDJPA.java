package com.petclinic.repository.sdjpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Owner;

public interface OwnerRepositorySDJPA extends CrudRepository<Owner, Long> {
	
	 Owner findByLastName(String lastName);
	 
	 List<Owner> findAllByLastNameLike(String lastName);

}
