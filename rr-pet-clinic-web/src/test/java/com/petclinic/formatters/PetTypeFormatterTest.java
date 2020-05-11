package com.petclinic.formatters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petclinic.model.PetType;
import com.petclinic.service.PetTypeService;

@ExtendWith(MockitoExtension.class)
public class PetTypeFormatterTest {

	@Mock
	PetTypeService petTypeService;

	@InjectMocks
	PetTypeFormatter petTypeFormatter;

	Locale locale;

	Set<PetType> petTypes;

	@BeforeEach
	public void setUp() {

		locale = new Locale("en");
		
//		petTypeFormatter = new PetTypeFormatter(petTypeService);

		PetType pet = new PetType();
		pet.setId(1L);
		pet.setName("Dog");

		PetType pet2 = new PetType();
		pet2.setId(2L);
		pet2.setName("Cat");

		petTypes = new HashSet<>();
		petTypes.add(pet);
		petTypes.add(pet2);

	}

	@Test
	public void print() {
		
		PetType petType = new PetType();
		petType.setId(1L);
		petType.setName("Dog");
		
		String retName = petTypeFormatter.print(petType, locale);
		
		assertEquals(retName, petType.getName());
	}
	
	@Test
	public void parse() throws ParseException {
		
		String type = "Dog";
		
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		PetType petType = petTypeFormatter.parse(type, locale);
		
		assertEquals(type, petType.getName());
	}

}
