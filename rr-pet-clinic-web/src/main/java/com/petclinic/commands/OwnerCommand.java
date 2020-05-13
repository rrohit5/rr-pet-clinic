package com.petclinic.commands;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
//@ToString

public class OwnerCommand {

	private String id;
	
//	@NotEmpty
	private String firstName;
	
//	@NotEmpty
    private String lastName;
	
//	@NotEmpty
    private String address;
	
//	@NotEmpty
    private String city;
	
//	@NotEmpty
//	@Digits(fraction = 0, integer = 10)
    private String telephone;
	
	
    private Set<PetCommand> pets = new HashSet<>();

    
    public void addPet(PetCommand pet) {
    	pet.setOwner(this);
    	this.pets.add(pet);        
    }

    public boolean isNew() {
        return this.id == null;
    }
    
    
    
    

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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
