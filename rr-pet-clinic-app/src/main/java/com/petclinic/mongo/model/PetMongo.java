package com.petclinic.mongo.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;

import org.springframework.core.style.ToStringCreator;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString

@Document
public class PetMongo{

	@Id
	private String id;
	private String name;
	private LocalDate birthDate;
	
	@DBRef
	private PetTypeMongo petType;
	
	private String owner;
	
	@Builder.Default
	private Set<String> visits = new HashSet<>();
	
	public boolean isNew() {
        return this.id == null;
    }

	public PetMongo addVisit(VisitMongo visit) {
		
		if(!this.visits.contains(visit.getId())) {
			
			getVisits().add(visit.getId());
			visit.setPet(this.getId());
			
		}
		
		return this;
	}
	
    @Override
    public String toString() {
    	
        return new ToStringCreator(this)

            .append("id", this.getId())
            .append("name", this.getName())
            .append("type", this.getPetType())
            .toString();
    }
	
}
