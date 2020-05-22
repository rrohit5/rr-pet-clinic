package com.petclinic.repository.sdjpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.petclinic.model.Owner;

public interface OwnerRepositorySDJPA extends Repository<Owner, Long> {
	
	 Owner findByLastName(String lastName);
	 
	 List<Owner> findAllByLastNameLike(String lastName);
	 
	 public Iterable<Owner> findAll();
	 
	 public Optional<Owner> findById(Long id);
	 
	 public Owner save(Owner owner);
	 
	 public void deleteById(Long id);
	 
	 public void delete(Owner owner);
	 
	 public long count();

}
