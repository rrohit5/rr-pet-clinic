package com.petclinic.service;

import java.util.List;

import com.petclinic.model.Visit;

public interface VisitService extends CrudService<Visit, Long> {

	List<Visit> findVisitsByPetId(Long petId);

}
