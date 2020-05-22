package com.petclinic.mongo.model;

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
public class PetTypeMongo {
	
	@Id
	private String id;
	private String name;
	
	public boolean isNew() {
        return this.id == null;
    }
	
}
