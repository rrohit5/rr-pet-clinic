package com.petclinic.repository.jdbc;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.petclinic.model.Vet;

public interface VetRepository {

	List<Vet> findAll() throws DataAccessException;

}
