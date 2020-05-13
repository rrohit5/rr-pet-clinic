package com.petclinic.service;

import java.util.List;

import com.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{//<T, ID>--satisfy the generic declaration for the class.

//	Owner findById(Long id);
//	Owner save(Owner owner);
//	Set<Owner> findAll();
	
	/*All the above common methods have been moved to CrudService 
	and make this class extend the CrudService class.*/
	
	List<Owner> findAllByLastNameLike(String lastName);

	Owner findByLastName(String lastName);
}
