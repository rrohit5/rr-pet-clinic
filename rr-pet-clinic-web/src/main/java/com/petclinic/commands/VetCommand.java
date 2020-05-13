package com.petclinic.commands;

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

public class VetCommand {
	
	private String id;
	
//	@NotEmpty
	private String firstName;
	
//	@NotEmpty
    private String lastName;
	
	private Set<SpecialtyCommand> specialties = new HashSet<>();


}
