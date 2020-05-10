package com.petclinic.commands;

import java.time.LocalDate;

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

public class VisitCommand {

	private Long id;
	private LocalDate date;
	private String description;
	private PetCommand pet;

    public LocalDate getDate() {
        return date;
    }
    
}
