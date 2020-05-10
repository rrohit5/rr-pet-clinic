package com.petclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "pets")
public class Pet extends BaseEntity {

	// @Builder
	public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
		super(id);
		this.name = name;
		this.petType = petType;
		this.owner = owner;
		this.birthDate = birthDate;
		// this.visits = visits;

		if (visits == null || visits.size() > 0) {
			this.visits = visits;
		}
	}

	@Column(name = "name")
	private String name;

	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name = "type_id")
	private PetType petType;

	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@Column(name = "birth_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet"/*, fetch = FetchType.LAZY*/)
	private Set<Visit> visits = new HashSet<>();

	public Pet addVisit(Visit visit) {
		
		if(!this.visits.contains(visit)) {
			
			getVisits().add(visit);
			visit.setPet(this);
			
		}
		

		return this;
	}
	
	public void setOwner(Owner owner) {
		
		this.owner = owner;
		
		if(owner != null && !(owner.getPets().contains(this))) {
			owner.getPets().add(this);
		}
		
	}


}
