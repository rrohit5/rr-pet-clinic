package com.petclinic.dto;

import java.time.LocalDate;
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

public class PetDTO{

	public PetDTO(String id) {
		super();
		this.id = id;
	}

	private String id;
	private String name;
	private LocalDate birthDate;
	private PetTypeDTO petType;
	private OwnerDTO owner;
	
	@Builder.Default
	private Set<VisitDTO> visits = new HashSet<>();
	
	public boolean isNew() {
        return this.id == null;
    }

	public PetDTO addVisit(VisitDTO visit) {
		
		if(!this.visits.contains(visit)) {
			
			getVisits().add(visit);
			visit.setPet(this);
			
		}
		

		return this;
	}
	
	public void setOwner(OwnerDTO owner) {
		
		this.owner = owner;
		
		if(owner != null && !(owner.getPets().contains(this))) {
			owner.getPets().add(this);
		}
		
	}
	
	public List<VisitDTO> getVisitsList() {
        List<VisitDTO> sortedVisits = new ArrayList<>(getVisits());
        PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
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
