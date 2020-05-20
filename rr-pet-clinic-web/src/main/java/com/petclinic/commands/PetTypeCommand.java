package com.petclinic.commands;

import javax.validation.constraints.NotEmpty;

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

public class PetTypeCommand {

	private String id;
	
	@NotEmpty
    private String name;

	@Override
    public String toString() {
        return name;
    }
}
