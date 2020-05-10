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

	private Long id;
    private String name;

	@Override
    public String toString() {
        return name;
    }
}
