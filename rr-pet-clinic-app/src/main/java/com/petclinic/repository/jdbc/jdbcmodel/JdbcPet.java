package com.petclinic.repository.jdbc.jdbcmodel;

import com.petclinic.model.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JdbcPet extends Pet {

    private Long typeId;
    private Long ownerId;
}
