package com.petclinic.mongo.repository;

import org.springframework.data.repository.CrudRepository;

import com.petclinic.mongo.model.VisitMongo;

//@Profile({"embeded_mongo", "docker_mongo"})
public interface VisitRepositoryMongo extends CrudRepository<VisitMongo, String> {

}
