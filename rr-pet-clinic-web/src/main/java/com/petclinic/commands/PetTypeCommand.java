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

public class PetTypeCommand {

	private String id;
	
//	@NotEmpty
    private String name;

	@Override
    public String toString() {
        return name;
    }
}
