package com.petclinic.mongo.repository;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.mongo.model.SpecialtyMongo;

//@Profile({"embeded_mongo", "docker_mongo"})
public interface SpecialtyRepositoryMongo extends CrudRepository<SpecialtyMongo, String> {

}
