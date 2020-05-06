package com.petclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
