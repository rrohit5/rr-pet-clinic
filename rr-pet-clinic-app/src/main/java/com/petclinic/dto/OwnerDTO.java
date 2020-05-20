package com.petclinic.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
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

public class OwnerDTO{
	
	private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    
    @Builder.Default
    private Set<PetDTO> pets = new HashSet<>();
//@Builder will ignore the initializing expression entirely.
//If you want the initializing expression to serve as default, add @Builder.Default.
//If it is not supposed to be settable during building, make the field final.

    public OwnerDTO(String id) {
		super();
		this.id = id;
	}
    
    public OwnerDTO addPet(PetDTO pet) {
    	
    	if(!this.pets.contains(pet)) {
    		
    		this.pets.add(pet);
            pet.setOwner(this);
    		
    	}        
        
        return this;
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public PetDTO getPet(String name) {
        return getPet(name, false);
    }
    
    
    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public PetDTO getPet(String name, boolean ignoreNew) {
    	
        name = name.toLowerCase();
        
        for (PetDTO pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
            	
                String compName = pet.getName();
                compName = compName.toLowerCase();
                
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        
        return null;
    }
    
    
    public List<PetDTO> getPetsList() {
        List<PetDTO> sortedPets = new ArrayList<>(getPets());
        PropertyComparator.sort(sortedPets, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedPets);
    }
    
    public boolean isNew() {
        return this.id == null;
    }

	@Override
    public String toString() {
    	
        return new ToStringCreator(this)

            .append("id", this.getId())
            .append("new", this.isNew())
            .append("lastName", this.getLastName())
            .append("firstName", this.getFirstName())
            .append("no. of pets", this.getPets().size())
            .toString();
    }


}
