package com.petclinic.mongo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;

import org.springframework.core.style.ToStringCreator;

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

//@Document
public class OwnerMongo{
	
	@Id
	private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;

    @Builder.Default
    private Set<String> pets = new HashSet<>();


    public boolean isNew() {
        return this.id == null;
    }
    
            
    
    @Override
    public String toString() {
    	
        return new ToStringCreator(this)

            .append("id", this.getId())
            .append("lastName", this.getLastName())
            .append("firstName", this.getFirstName())
            .append("no. of pets", this.getPets().size())
            .toString();
    }


}
