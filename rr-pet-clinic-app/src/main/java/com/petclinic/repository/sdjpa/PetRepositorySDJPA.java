package com.petclinic.repository.sdjpa;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Pet;

public interface PetRepositorySDJPA extends CrudRepository<Pet, Long> {

}
