package com.petclinic.commands;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

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

public class PetCommand {

	private String id;

    @NotEmpty
	private String name;

	private PetTypeCommand petType;

	private OwnerCommand owner;

	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@Builder.Default
	private Set<VisitCommand> visits = new HashSet<>();

	public void addVisit(VisitCommand visit) {
		getVisits().add(visit);
		visit.setPet(this);
	}

	public boolean isNew() {
		return this.id == null;
	}
}
