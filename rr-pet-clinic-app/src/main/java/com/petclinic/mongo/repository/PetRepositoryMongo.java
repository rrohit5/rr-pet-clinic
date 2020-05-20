package com.petclinic.mongo.repository;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.mongo.model.PetMongo;

//@Profile({"embeded_mongo", "docker_mongo"})
public interface PetRepositoryMongo extends CrudRepository<PetMongo, String> {

}
