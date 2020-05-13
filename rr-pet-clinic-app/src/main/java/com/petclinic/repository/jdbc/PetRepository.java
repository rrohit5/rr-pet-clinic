package com.petclinic.repository.jdbc;

import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.petclinic.model.Pet;

public interface PetRepository {

    Optional<Pet> findById(Long id) throws DataAccessException;

    Pet save(Pet pet) throws DataAccessException;

}
