package com.petclinic.convertor;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petclinic.commands.OwnerCommand;
import com.petclinic.commands.PetCommand;
import com.petclinic.commands.PetTypeCommand;
import com.petclinic.commands.SpecialtyCommand;
import com.petclinic.commands.VetCommand;
import com.petclinic.commands.VisitCommand;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.dto.PetDTO;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.dto.SpecialtyDTO;
import com.petclinic.dto.VetDTO;
import com.petclinic.dto.VisitDTO;

class CommandDTOConvertorTest {

	private CommandDTOConvertor convertor;

	private PetTypeDTO dogPetTypeDTO;
	private PetTypeDTO catPetTypeDTO;
	private PetDTO dogPetDTO;
	private PetDTO catPetDTO;
	private OwnerDTO ownerDTO1;
	private VisitDTO dogVisitDTO1;
	private VisitDTO dogVisitDTO2;
	private VisitDTO catVisitDTO1;
	private VisitDTO catVisitDTO2;
	private SpecialtyDTO specialtyDTO1;
	private SpecialtyDTO specialtyDTO2;
	private VetDTO vetDTO1;

	private PetTypeCommand dogPetTypeCommand;
	private PetTypeCommand catPetTypeCommand;
	private PetCommand dogPetCommand;
	private PetCommand catPetCommand;
	private OwnerCommand ownerCommand1;
	private VisitCommand dogVisitCommand1;
	private VisitCommand dogVisitCommand2;
	private VisitCommand catVisitCommand1;
	private VisitCommand catVisitCommand2;
	private SpecialtyCommand specialtyCommand1;
	private SpecialtyCommand specialtyCommand2;
	private VetCommand vetCommand1;

	private PetTypeDTO petTypeDTOWithNullId;
	private PetDTO petDTOWithNullId;
	private OwnerDTO ownerDTOWithNullId;
	private VisitDTO visitDTOWithNullId;
	private SpecialtyDTO specialtyDTOWithNullId;
	private VetDTO vetDTOWithNullId;

	private PetTypeCommand petTypeWithNullId;
	private PetCommand petWithNullId;
	private OwnerCommand ownerWithNullId;
	private VisitCommand visitWithNullId;
	private SpecialtyCommand specialtyWithNullId;
	private VetCommand vetWithNullId;

	private OwnerDTO ownerDTOForNewPetTest;
	private OwnerCommand ownerForNewPetTest;

	private PetDTO dogPetDTOForNewVisitTest;
	private PetCommand dogPetForNewVisitTest;

	@BeforeEach
	void setUp() throws Exception {
		
		convertor = new CommandDTOConvertor();

		dogPetTypeDTO = new PetTypeDTO("1", "Dog");
		catPetTypeDTO = new PetTypeDTO("2", "Cat");

		dogPetDTO = PetDTO.builder().id("1").birthDate(LocalDate.now()).name("pet1name").petType(dogPetTypeDTO).build();
		catPetDTO = PetDTO.builder().id("2").birthDate(LocalDate.now()).name("pet2name").petType(catPetTypeDTO).build();

		ownerDTO1 = OwnerDTO.builder().id("1").address("ownerAddress1").city("ownerCity1").firstName("ownerFirstName1")
				.lastName("ownerLastName1").telephone("ownerTelephone1").build();

		ownerDTO1.addPet(dogPetDTO);
		ownerDTO1.addPet(catPetDTO);

		dogVisitDTO1 = VisitDTO.builder().id("1").date(LocalDate.now()).description("visitDescription1").pet(dogPetDTO).build();
		dogVisitDTO2 = VisitDTO.builder().id("2").date(LocalDate.now()).description("visitDescription2").pet(dogPetDTO).build();
		catVisitDTO1 = VisitDTO.builder().id("3").date(LocalDate.now()).description("visitDescription3").pet(catPetDTO).build();
		catVisitDTO2 = VisitDTO.builder().id("4").date(LocalDate.now()).description("visitDescription4").pet(catPetDTO).build();
		dogPetDTO.addVisit(dogVisitDTO1);
		dogPetDTO.addVisit(dogVisitDTO2);
		catPetDTO.addVisit(catVisitDTO1);
		catPetDTO.addVisit(catVisitDTO2);

		specialtyDTO1 = SpecialtyDTO.builder().id("1").name("specialtyName1").build();
		specialtyDTO2 = SpecialtyDTO.builder().id("2").name("specialtyName2").build();

		vetDTO1 = VetDTO.builder().id("1").firstName("vetFirstName1").lastName("vetLastName1").build();
		vetDTO1.addSpecialty(specialtyDTO1);
		vetDTO1.addSpecialty(specialtyDTO2);

		dogPetTypeCommand = new PetTypeCommand("1", "Dog");
		catPetTypeCommand = new PetTypeCommand("2", "Cat");

		dogPetCommand = PetCommand.builder().id("1").birthDate(LocalDate.now()).name("pet1name").petType(dogPetTypeCommand).build();
		catPetCommand = PetCommand.builder().id("2").birthDate(LocalDate.now()).name("pet2name").petType(catPetTypeCommand).build();

		ownerCommand1 = OwnerCommand.builder().id("1").address("ownerAddress1").city("ownerCity1")
				.firstName("ownerFirstName1").lastName("ownerLastName1").telephone("ownerTelephone1").build();

		ownerCommand1.addPet(dogPetCommand);
		ownerCommand1.addPet(catPetCommand);

		dogVisitCommand1 = VisitCommand.builder().id("1").date(LocalDate.now()).description("visitDescription1").pet(dogPetCommand).build();
		dogVisitCommand2 = VisitCommand.builder().id("2").date(LocalDate.now()).description("visitDescription2").pet(dogPetCommand).build();
		catVisitCommand1 = VisitCommand.builder().id("3").date(LocalDate.now()).description("visitDescription3").pet(catPetCommand).build();
		catVisitCommand2 = VisitCommand.builder().id("4").date(LocalDate.now()).description("visitDescription4").pet(catPetCommand).build();
		dogPetCommand.addVisit(dogVisitCommand1);
		dogPetCommand.addVisit(dogVisitCommand2);
		catPetCommand.addVisit(catVisitCommand1);
		catPetCommand.addVisit(catVisitCommand2);

		specialtyCommand1 = SpecialtyCommand.builder().id("1").specialtyName("specialtyName1").build();
		specialtyCommand2 = SpecialtyCommand.builder().id("2").specialtyName("specialtyName2").build();

		vetCommand1 = VetCommand.builder().id("1").firstName("vetFirstName1").lastName("vetLastName1").build();
		vetCommand1.addSpecialty(specialtyCommand1);
		vetCommand1.addSpecialty(specialtyCommand2);

		// test null ids

		ownerDTOForNewPetTest = OwnerDTO.builder().id("2").address("ownerAddress2").city("ownerCity2")
				.firstName("ownerFirstName2").lastName("ownerLastName2").telephone("ownerTelephone2").build();
		ownerForNewPetTest = OwnerCommand.builder().id("2").address("ownerAddress2").city("ownerCity2")
				.firstName("ownerFirstName2").lastName("ownerLastName2").telephone("ownerTelephone2").build();

		dogPetDTOForNewVisitTest = PetDTO.builder().id("3").birthDate(LocalDate.now()).name("pet3name").petType(dogPetTypeDTO).build();
		dogPetForNewVisitTest = PetCommand.builder().id("3").birthDate(LocalDate.now()).name("pet3name").petType(dogPetTypeCommand).build();

		petTypeDTOWithNullId = new PetTypeDTO(null, "Dog");
		petDTOWithNullId = PetDTO.builder().id(null).birthDate(LocalDate.now()).name("pet1name").petType(dogPetTypeDTO).build();
		ownerDTOWithNullId = OwnerDTO.builder().id(null).address("ownerAddress1").city("ownerCity1")
				.firstName("ownerFirstName1").lastName("ownerLastName1").telephone("ownerTelephone1").build();

		ownerDTOForNewPetTest.addPet(petDTOWithNullId);

		visitDTOWithNullId = VisitDTO.builder().id(null).date(LocalDate.now()).description("visitDescription1").pet(dogPetDTOForNewVisitTest).build();
		specialtyDTOWithNullId = SpecialtyDTO.builder().id(null).name("specialtyName1").build();
		vetDTOWithNullId = VetDTO.builder().id(null).firstName("vetFirstName1").lastName("vetLastName1").build();
		vetDTOWithNullId.addSpecialty(specialtyDTO1);
		vetDTOWithNullId.addSpecialty(specialtyDTO2);

		petTypeWithNullId = new PetTypeCommand(null, "Dog");
		petWithNullId = PetCommand.builder().id(null).birthDate(LocalDate.now()).name("pet1name").petType(dogPetTypeCommand).build();
		ownerWithNullId = OwnerCommand.builder().id(null).address("ownerAddress1").city("ownerCity1")
				.firstName("ownerFirstName1").lastName("ownerLastName1").telephone("ownerTelephone1").build();

		ownerForNewPetTest.addPet(petWithNullId);

		visitWithNullId = VisitCommand.builder().id(null).date(LocalDate.now()).description("visitDescription1").pet(dogPetForNewVisitTest).build();
		specialtyWithNullId = SpecialtyCommand.builder().id(null).specialtyName("specialtyName1").build();
		vetWithNullId = VetCommand.builder().id(null).firstName("vetFirstName1").lastName("vetLastName1").build();
		vetWithNullId.getSpecialties().add(specialtyCommand1);
		vetWithNullId.getSpecialties().add(specialtyCommand2);
	}

	private boolean equalsWithNulls(Object a, Object b) {
		if (a == b)
			return true;
		if ((a == null) || (b == null))
			return false;
		return a.equals(b);
	}

	private boolean testOwnerEquals(OwnerDTO dto, OwnerCommand entity) {

		if (equalsWithNulls(dto.getAddress(), entity.getAddress()) && equalsWithNulls(dto.getCity(), entity.getCity())
				&& equalsWithNulls(dto.getFirstName(), entity.getFirstName())
				&& equalsWithNulls(dto.getLastName(), entity.getLastName())
				&& equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
				&& equalsWithNulls(dto.getTelephone(), entity.getTelephone())) {

			Set<PetDTO> dtoPets = dto.getPets();
			Set<PetCommand> entityPets = entity.getPets();

			if (dtoPets.size() == entityPets.size()) {

				for (PetDTO petDTO : dtoPets) {

					if (!testPetEquals(petDTO, entity.getPet(petDTO.getName()))) {

						return false;
					}

				}
				return true;
			}

			return false;
		}
		return false;
	}

	private boolean testPetEquals(PetDTO dto, PetCommand entity) {

		if (equalsWithNulls(dto.getBirthDate(), entity.getBirthDate())
				&& equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
				&& equalsWithNulls(dto.getName(), entity.getName())) {

			if (!testPetTypeEquals(dto.getPetType(), entity.getPetType())) {
				return false;
			}

			Set<VisitDTO> dtoVisits = dto.getVisits();
			Set<VisitCommand> entityVisit = entity.getVisits();

			if (dtoVisits.size() == entityVisit.size()) {

				return true;
			}
		}

		return false;
	}

	private boolean testVisitEquals(VisitDTO dto, VisitCommand entity) {

		if (equalsWithNulls(dto.getDescription(), entity.getDescription())
				&& equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
				&& equalsWithNulls(dto.getDate(), entity.getDate())) {
			return true;
		}

		return false;
	}

	private boolean testSpecialtyEquals(SpecialtyDTO dto, SpecialtyCommand entity) {

		if (equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
				&& equalsWithNulls(dto.getName(), entity.getSpecialtyName())) {

			return true;
		}

		return false;
	}

	private boolean testVetEquals(VetDTO dto, VetCommand entity) {

		if (equalsWithNulls(dto.getFirstName(), entity.getFirstName())
				&& equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
				&& equalsWithNulls(dto.getLastName(), entity.getLastName())) {

			Set<SpecialtyDTO> dtoSpecialties = dto.getSpecialties();
			Set<SpecialtyCommand> entitySpecialties = entity.getSpecialties();

			if (dtoSpecialties.size() == entitySpecialties.size()) {

				return true;
			}

			return false;
		}

		return false;
	}

	private boolean testPetTypeEquals(PetTypeDTO dto, PetTypeCommand entity) {

		if (equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
				&& equalsWithNulls(dto.getName(), entity.getName())) {
			return true;
		}

		return false;
	}

	@Test
	void testConvertOwnerCommand() {

		OwnerDTO dest = convertor.convert(ownerCommand1);
		assertTrue(testOwnerEquals(dest, ownerCommand1));

		dest = convertor.convert(ownerWithNullId);
		assertTrue(testOwnerEquals(dest, ownerWithNullId));
	}

	@Test
	void testConvertOwnerDTO() {

		OwnerCommand dest = convertor.convert(ownerDTO1);
		assertTrue(testOwnerEquals(ownerDTO1, dest));

		dest = convertor.convert(ownerDTOWithNullId);
		assertTrue(testOwnerEquals(ownerDTOWithNullId, dest));
	}

	@Test
	void testConvertPetCommand() {

		PetDTO dest = convertor.convert(dogPetCommand);
		assertTrue(testPetEquals(dest, dogPetCommand));

		dest = convertor.convert(petWithNullId);
		assertTrue(testPetEquals(dest, petWithNullId));
	}

	@Test
	void testConvertPetDTO() {

		PetCommand dest = convertor.convert(dogPetDTO);
		assertTrue(testPetEquals(dogPetDTO, dest));

		dest = convertor.convert(petDTOWithNullId);
		assertTrue(testPetEquals(petDTOWithNullId, dest));
	}

	@Test
	void testConvertPetTypeCommand() {

		PetTypeDTO dest = convertor.convert(dogPetTypeCommand);
		assertTrue(testPetTypeEquals(dest, dogPetTypeCommand));

		dest = convertor.convert(petTypeWithNullId);
		assertTrue(testPetTypeEquals(dest, petTypeWithNullId));
	}

	@Test
	void testConvertPetTypeDTO() {

		PetTypeCommand dest = convertor.convert(dogPetTypeDTO);
		assertTrue(testPetTypeEquals(dogPetTypeDTO, dest));

		dest = convertor.convert(petTypeDTOWithNullId);
		assertTrue(testPetTypeEquals(petTypeDTOWithNullId, dest));
	}

	@Test
	void testConvertSpecialtyCommand() {

		SpecialtyDTO dest = convertor.convert(specialtyCommand1);
		assertTrue(testSpecialtyEquals(dest, specialtyCommand1));

		dest = convertor.convert(specialtyWithNullId);
		assertTrue(testSpecialtyEquals(dest, specialtyWithNullId));
	}

	@Test
	void testConvertSpecialtyDTO() {

		SpecialtyCommand dest = convertor.convert(specialtyDTO1);
		assertTrue(testSpecialtyEquals(specialtyDTO1, dest));

		dest = convertor.convert(specialtyDTOWithNullId);
		assertTrue(testSpecialtyEquals(specialtyDTOWithNullId, dest));
	}

	@Test
	void testConvertVetCommand() {

		VetDTO dest = convertor.convert(vetCommand1);
		assertTrue(testVetEquals(dest, vetCommand1));

		dest = convertor.convert(vetWithNullId);
		assertTrue(testVetEquals(dest, vetWithNullId));
	}

	@Test
	void testConvertVetDTO() {

		VetCommand dest = convertor.convert(vetDTO1);
		assertTrue(testVetEquals(vetDTO1, dest));

		dest = convertor.convert(vetDTOWithNullId);
		assertTrue(testVetEquals(vetDTOWithNullId, dest));
	}

	@Test
	void testConvertVisitCommand() {

		VisitDTO dest = convertor.convert(dogVisitCommand1);
		assertTrue(testVisitEquals(dest, dogVisitCommand1));

		dest = convertor.convert(visitWithNullId);
		assertTrue(testVisitEquals(dest, visitWithNullId));
	}

	@Test
	void testConvertVisitDTO() {

		VisitCommand dest = convertor.convert(dogVisitDTO1);
		assertTrue(testVisitEquals(dogVisitDTO1, dest));

		dest = convertor.convert(visitDTOWithNullId);
		assertTrue(testVisitEquals(visitDTOWithNullId, dest));
	}

}
