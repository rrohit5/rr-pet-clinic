package com.petclinic.commands;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
//@ToString

public class OwnerCommand {

	private Long id;
	private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private Set<PetCommand> pets = new HashSet<>();


    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public PetCommand getPet(String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public PetCommand getPet(String name, boolean ignoreNew) {
    	
        name = name.toLowerCase();
        
        for (PetCommand pet : pets) {
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
    
    
    public void addPet(PetCommand pet) {
    	pet.setOwner(this);
    	this.pets.add(pet);        
    }

    public boolean isNew() {
        return this.id == null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
