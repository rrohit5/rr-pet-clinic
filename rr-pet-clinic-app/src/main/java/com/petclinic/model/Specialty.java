package com.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString

@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity {

	public Specialty(Long id, String name) {
		super(id, name);
	}

}
