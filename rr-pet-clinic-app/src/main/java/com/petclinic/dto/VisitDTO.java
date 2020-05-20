package com.petclinic.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString

public class VisitDTO {
	
	private String id;
	private LocalDate date;
	private String description;
	private PetDTO pet;
	
	public VisitDTO() {
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }
    
    public boolean isNew() {
        return this.id == null;
    }

	public VisitDTO(String id) {
		super();
		this.id = id;
	}
    
}
