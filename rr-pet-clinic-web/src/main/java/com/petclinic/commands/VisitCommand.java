package com.petclinic.commands;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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

public class VisitCommand {

	private String id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
//	@NotEmpty
	private String description;
	
	private PetCommand pet;

    public LocalDate getDate() {
        return date;
    }
    
    public boolean isNew() {
		return this.id == null;
	}
}
