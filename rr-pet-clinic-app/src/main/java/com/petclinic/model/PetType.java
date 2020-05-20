package com.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
//@Builder

@Entity
@Table(name = "types")
public class PetType  extends NamedEntity {
	
	public PetType(Long id, String name) {
		super(id, name);	
	}
}
