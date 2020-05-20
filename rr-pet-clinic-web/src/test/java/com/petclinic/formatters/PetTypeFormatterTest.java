package com.petclinic.formatters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petclinic.commands.PetTypeCommand;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.service.PetTypeService;

@ExtendWith(MockitoExtension.class)
public class PetTypeFormatterTest {

	@Mock
	PetTypeService petTypeService;

	@InjectMocks
	PetTypeFormatter petTypeFormatter;

	Locale locale;

	List<PetTypeDTO> petTypes;

	@BeforeEach
	public void setUp() {

		locale = new Locale("en");
		
//		petTypeFormatter = new PetTypeFormatter(petTypeService);

		PetTypeDTO pet = new PetTypeDTO();
		pet.setId("1");
		pet.setName("Dog");

		PetTypeDTO pet2 = new PetTypeDTO();
		pet2.setId("2");
		pet2.setName("Cat");

		petTypes = new ArrayList<>();
		petTypes.add(pet);
		petTypes.add(pet2);

	}

	@Test
	public void print() {
		
		PetTypeCommand petType = new PetTypeCommand();
		petType.setId("1");
		petType.setName("Dog");
		
		String retName = petTypeFormatter.print(petType, locale);
		
		assertEquals(retName, petType.getName());
	}
	
	@Test
	public void parse() throws ParseException {
		
		String type = "Dog";
		
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		PetTypeCommand petType = petTypeFormatter.parse(type, locale);
		
		assertEquals(type, petType.getName());
	}

}
