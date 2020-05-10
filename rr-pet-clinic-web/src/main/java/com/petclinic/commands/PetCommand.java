package com.petclinic.commands;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
//@ToString

public class PetCommand {
	
    private Long id;
    private String name;
    private PetTypeCommand petType;
    private OwnerCommand owner;
    private LocalDate birthDate;
    private Set<VisitCommand> visits = new HashSet<>();

	
    public void addVisit(VisitCommand visit) {
        getVisits().add(visit);
        visit.setPet(this);
    }
    
    public boolean isNew() {
        return this.id == null;
    }
}
