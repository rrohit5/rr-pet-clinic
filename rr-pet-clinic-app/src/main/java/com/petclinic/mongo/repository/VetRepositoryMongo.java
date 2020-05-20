package com.petclinic.mongo.repository;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.mongo.model.VetMongo;

//@Profile({"embeded_mongo", "docker_mongo"})
public interface VetRepositoryMongo extends CrudRepository<VetMongo, String> {

}
