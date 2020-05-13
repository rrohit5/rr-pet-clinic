package com.petclinic.repository.sdjpa;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.PetType;

public interface PetTypeRepositorySDJPA extends CrudRepository<PetType, Long> {

}
