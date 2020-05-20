package com.petclinic.service;

import com.petclinic.dto.VetDTO;

public interface VetService extends CrudService<VetDTO, String> {//<T, ID>--satisfy the generic declaration for the class.
	
//	Vet findById(Long id);
//	Vet save(Vet vet);
//	Set<Vet> findAll();
	
	/*All the above common methods have been moved to CrudService 
	and make this class extend the CrudService class.*/
	
	
}
