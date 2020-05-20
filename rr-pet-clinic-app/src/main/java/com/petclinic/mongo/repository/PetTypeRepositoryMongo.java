package com.petclinic.mongo.repository;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.mongo.model.PetTypeMongo;

//@Profile({"embeded_mongo", "docker_mongo"})
public interface PetTypeRepositoryMongo extends CrudRepository<PetTypeMongo, String> {

}
