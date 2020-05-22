package com.petclinic.mongo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;

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
@Builder
@ToString

//@Document
public class VetMongo {
	
	@Id
	private String id;
    private String firstName;
    private String lastName;
    
//    @DBRef
    @Builder.Default
	private Set<SpecialtyMongo> specialties = new HashSet<>();

	
	public boolean isNew() {
        return this.id == null;
    }

	
}
