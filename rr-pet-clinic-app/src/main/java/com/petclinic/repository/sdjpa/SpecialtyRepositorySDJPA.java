package com.petclinic.repository.sdjpa;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Specialty;

public interface SpecialtyRepositorySDJPA extends CrudRepository<Specialty, Long> {

}
