package com.petclinic.repository.sdjpa;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Vet;

public interface VetRepositorySDJPA extends CrudRepository<Vet, Long> {

}
