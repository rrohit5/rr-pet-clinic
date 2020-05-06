package com.petclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Specialty;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {

}
