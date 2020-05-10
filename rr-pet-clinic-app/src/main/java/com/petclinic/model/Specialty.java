package com.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
//@ToString

@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity {
	
	@Column(name = "name")
	private String specialityName;

  

}
