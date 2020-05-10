package com.petclinic.commands;

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

public class SpecialtyCommand {
	
	private Long id;
	private String specialityName;

}
