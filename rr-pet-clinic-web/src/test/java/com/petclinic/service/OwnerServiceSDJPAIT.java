package com.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.petclinic.dto.OwnerDTO;
import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.service.sdjpa.OwnerServiceSDJPA;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"h2","sdjpa"})
@EnableAutoConfiguration(exclude=EmbeddedMongoAutoConfiguration.class)
class OwnerServiceSDJPAIT {
	
	@Autowired
	OwnerServiceSDJPA ownerService;
	
	private Owner owner1;
	private PetType dogPetType;
	private Pet dogPet;

	@BeforeEach
	void setUp() throws Exception {
		
		owner1 = Owner.builder().id(null).address("ownerAddress1").city("ownerCity1").firstName("ownerFirstName1").lastName("ownerLastName1")
				.telephone("ownerTelephone1").build();
		
		dogPetType = new PetType(1l, "Dog");
		dogPet = Pet.builder().id(1l).birthDate(LocalDate.now()).name("pet1name").petType(dogPetType).build();
	}

	@Test
	void testOwnerServiceSDJPA() {
		
	}

	@Test
	void testFindByLastName() {
		
	}

	@Test
	void testFindAllByLastNameLike() {
		
	}

	@Test
	@Transactional
	void testFindAll() {

		List<OwnerDTO> dto = ownerService.findAll();
		
		assertTrue(dto.size() > 0);
	}

	@Test
	void testFindById() {
		
	}

	@Test
	void testSave() {
		
	}

	@Test
	void testDelete() {
		
	}

	@Test
	void testDeleteById() {
		
	}

}
