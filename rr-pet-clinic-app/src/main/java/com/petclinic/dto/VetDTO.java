package com.petclinic.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

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

public class VetDTO {
	
	private String id;
    private String firstName;
    private String lastName;
    
    @Builder.Default
	private Set<SpecialtyDTO> specialties = new HashSet<>();

	@XmlElement
	public List<SpecialtyDTO> getSpecialtiesList() {
		List<SpecialtyDTO> sortedSpecs = new ArrayList<>(getSpecialties());
		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedSpecs);
	}
	
	public boolean isNew() {
        return this.id == null;
    }

	public int getNrOfSpecialties() {
		return getSpecialties().size();
	}

	public void addSpecialty(SpecialtyDTO specialty) {
		getSpecialties().add(specialty);
	}
	
	
	
}
