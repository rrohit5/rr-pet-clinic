package com.petclinic.service;

import com.petclinic.model.Pet;

public interface PetService extends CrudService<Pet, Long> {//<T, ID>--satisfy the generic declaration for the class.

//	Pet findById(Long id);
//	Pet save(Pet pet);
//	Set<Pet> findAll();
	
	/*All the above common methods have been moved to CrudService 
	and make this class extend the CrudService class.*/
	
	
}
