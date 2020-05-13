package com.petclinic.repository.sdjpa;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.model.Visit;

public interface VisitRepositorySDJPA extends CrudRepository<Visit, Long> {

}
