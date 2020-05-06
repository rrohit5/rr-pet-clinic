package com.petclinic.repository;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {

}
