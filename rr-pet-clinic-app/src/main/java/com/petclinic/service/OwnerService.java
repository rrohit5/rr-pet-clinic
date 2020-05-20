package com.petclinic.service;

import java.util.List;

import com.petclinic.dto.OwnerDTO;

public interface OwnerService extends CrudService<OwnerDTO, String>{//<T, ID>--satisfy the generic declaration for the class.

//	Owner findById(Long id);
//	Owner save(Owner owner);
//	Set<Owner> findAll();
	
	/*All the above common methods have been moved to CrudService 
	and make this class extend the CrudService class.*/
	
	List<OwnerDTO> findAllByLastNameLike(String lastName);

	OwnerDTO findByLastName(String lastName);
}
