package com.petclinic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
//@ToString

@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {

	@Column(name = "birth_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate birthDate;
		
	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name = "type_id")
	private PetType petType;

	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.LAZY)
	@Builder.Default
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
	
	public List<Visit> getVisitsList() {
        List<Visit> sortedVisits = new ArrayList<>(getVisits());
        PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }


}
