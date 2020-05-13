package com.petclinic.repository.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.petclinic.model.Owner;

public interface OwnerRepository {

    List<Owner> findAllByLastNameLike(String lastName) throws DataAccessException;

    Optional<Owner> findById(Long id) throws DataAccessException;

    Owner save(Owner owner) throws DataAccessException;
}
