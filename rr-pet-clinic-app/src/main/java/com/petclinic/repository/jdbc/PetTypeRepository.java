package com.petclinic.repository.jdbc;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.petclinic.model.PetType;

public interface PetTypeRepository {
	
	List<PetType> findPetTypes() throws DataAccessException;

	PetType save(PetType petType) throws DataAccessException;

}
