package com.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

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
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner"/*, fetch = FetchType.LAZY*/)
    @Builder.Default
    private Set<Pet> pets = new HashSet<>();


    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name, boolean ignoreNew) {
    	
        name = name.toLowerCase();
        
        for (Pet pet : pets) {
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
    
    
    public List<Pet> getPetsList() {
        List<Pet> sortedPets = new ArrayList<>(getPets());
        PropertyComparator.sort(sortedPets, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedPets);
    }
    
    
    public Owner addPet(Pet pet) {
    	
    	if(!this.pets.contains(pet)) {
    		
    		this.pets.add(pet);
            pet.setOwner(this);
    		
    	}        
        
        return this;
    }
    
    @Override
    public String toString() {
    	
        return new ToStringCreator(this)

            .append("id", this.getId())
            .append("new", this.isNew())
            .append("lastName", this.getLastName())
            .append("firstName", this.getFirstName())
            .append("address", this.address)
            .append("city", this.city)
            .append("telephone", this.telephone)
            .append("no. of pets", this.getPets().size())
            .toString();
    }


}
