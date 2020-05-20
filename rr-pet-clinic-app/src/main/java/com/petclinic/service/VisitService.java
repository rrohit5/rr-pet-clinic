package com.petclinic.service;

import java.util.List;

import com.petclinic.dto.VisitDTO;

public interface VisitService extends CrudService<VisitDTO, String> {

	List<VisitDTO> findVisitsByPetId(Long petId);

}
