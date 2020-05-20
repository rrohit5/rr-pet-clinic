package com.petclinic.convertor;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petclinic.dto.OwnerDTO;
import com.petclinic.dto.PetDTO;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.dto.SpecialtyDTO;
import com.petclinic.dto.VetDTO;
import com.petclinic.dto.VisitDTO;
import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.model.Specialty;
import com.petclinic.model.Vet;
import com.petclinic.model.Visit;
import com.petclinic.mongo.model.OwnerMongo;
import com.petclinic.mongo.model.PetMongo;
import com.petclinic.mongo.model.PetTypeMongo;
import com.petclinic.mongo.model.SpecialtyMongo;
import com.petclinic.mongo.model.VetMongo;
import com.petclinic.mongo.model.VisitMongo;

class ModelDTOConvertorTest {
	
	private ModelDTOConvertor modelDTOConvertor;
	
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
	
	private PetTypeMongo dogPetTypeMongo;
	private PetTypeMongo catPetTypeMongo;
	private PetMongo dogPetMongo;
	private PetMongo catPetMongo;
	private OwnerMongo ownerMongo1;
	private VisitMongo dogVisitMongo1;
	private VisitMongo dogVisitMongo2;
	private VisitMongo catVisitMongo1;
	private VisitMongo catVisitMongo2;
	private SpecialtyMongo specialtyMongo1;
	private SpecialtyMongo specialtyMongo2;
	private VetMongo vetMongo1;
	
	private PetType dogPetType;
	private PetType catPetType;
	private Pet dogPet;
	private Pet catPet;
	private Owner owner1;
	private Visit dogVisit1;
	private Visit dogVisit2;
	private Visit catVisit1;
	private Visit catVisit2;
	private Specialty specialty1;
	private Specialty specialty2;
	private Vet vet1;
	
	
	private PetTypeDTO petTypeDTOWithNullId;
	private PetDTO petDTOWithNullId;
	private OwnerDTO ownerDTOWithNullId;
	private VisitDTO visitDTOWithNullId;
	private SpecialtyDTO specialtyDTOWithNullId;
	private VetDTO vetDTOWithNullId;
	
	private PetType petTypeWithNullId;
	private Pet petWithNullId;
	private Owner ownerWithNullId;
	private Visit visitWithNullId;
	private Specialty specialtyWithNullId;
	private Vet vetWithNullId;
	
	private PetTypeMongo petTypeMongoWithNullId;
	private PetMongo petMongoWithNullId;
	private OwnerMongo ownerMongoWithNullId;
	private VisitMongo visitMongoWithNullId;
	private SpecialtyMongo specialtyMongoWithNullId;
	private VetMongo vetMongoWithNullId;
	private OwnerMongo ownerMongoForNewPetTest;
	
	private OwnerDTO ownerDTOForNewPetTest;	
	private Owner ownerForNewPetTest;

	private PetMongo dogPetMongoForNewVisitTest;
	private PetDTO dogPetDTOForNewVisitTest;
	private Pet dogPetForNewVisitTest;

	@BeforeEach
	void setUp() throws Exception {
		
		modelDTOConvertor = new ModelDTOConvertor();
		
		dogPetTypeDTO = new PetTypeDTO("1", "Dog");
		catPetTypeDTO = new PetTypeDTO("2", "Cat");
		
		dogPetDTO = PetDTO.builder().id("1").birthDate(LocalDate.now()).name("pet1name").petType(dogPetTypeDTO).build();
		catPetDTO = PetDTO.builder().id("2").birthDate(LocalDate.now()).name("pet2name").petType(catPetTypeDTO).build();
		
		ownerDTO1 = OwnerDTO.builder().id("1").address("ownerAddress1").city("ownerCity1").firstName("ownerFirstName1").lastName("ownerLastName1")
							.telephone("ownerTelephone1").build();
		
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
		
		
		
		
		
		
		
		dogPetTypeMongo = new PetTypeMongo("1", "Dog");
		catPetTypeMongo = new PetTypeMongo("2", "Cat");
		
		dogPetMongo = PetMongo.builder().id("1").birthDate(LocalDate.now()).name("pet1name").petType(dogPetTypeMongo).build();
		catPetMongo = PetMongo.builder().id("2").birthDate(LocalDate.now()).name("pet2name").petType(catPetTypeMongo).build();
		
		ownerMongo1 = OwnerMongo.builder().id("1").address("ownerAddress1").city("ownerCity1").firstName("ownerFirstName1").lastName("ownerLastName1")
							.telephone("ownerTelephone1").build();
		
		ownerMongo1.getPets().add(dogPetMongo.getId());
		ownerMongo1.getPets().add(catPetMongo.getId());
		
		dogPetMongo.setOwner(ownerMongo1.getId());
		catPetMongo.setOwner(ownerMongo1.getId());
		
		dogVisitMongo1 = VisitMongo.builder().id("1").date(LocalDate.now()).description("visitDescription1").pet(dogPetMongo.getId()).build();
		dogVisitMongo2 = VisitMongo.builder().id("2").date(LocalDate.now()).description("visitDescription2").pet(dogPetMongo.getId()).build();
		catVisitMongo1 = VisitMongo.builder().id("3").date(LocalDate.now()).description("visitDescription3").pet(catPetMongo.getId()).build();
		catVisitMongo2 = VisitMongo.builder().id("4").date(LocalDate.now()).description("visitDescription4").pet(catPetMongo.getId()).build();
		dogPetMongo.addVisit(dogVisitMongo1);
		dogPetMongo.addVisit(dogVisitMongo2);
		catPetMongo.addVisit(catVisitMongo1);
		catPetMongo.addVisit(catVisitMongo2);
		
		
		specialtyMongo1 = SpecialtyMongo.builder().id("1").name("specialtyName1").build();
		specialtyMongo2 = SpecialtyMongo.builder().id("2").name("specialtyName2").build();
		
		vetMongo1 = VetMongo.builder().id("1").firstName("vetFirstName1").lastName("vetLastName1").build();
		vetMongo1.getSpecialties().add(specialtyMongo1);
		vetMongo1.getSpecialties().add(specialtyMongo2);
		
		
		
		
		
		
		dogPetType = new PetType(1l, "Dog");
		catPetType = new PetType(2l, "Cat");
		
		dogPet = Pet.builder().id(1l).birthDate(LocalDate.now()).name("pet1name").petType(dogPetType).build();
		catPet = Pet.builder().id(2l).birthDate(LocalDate.now()).name("pet2name").petType(catPetType).build();
		
		owner1 = Owner.builder().id(1l).address("ownerAddress1").city("ownerCity1").firstName("ownerFirstName1").lastName("ownerLastName1")
							.telephone("ownerTelephone1").build();
		
		
		owner1.addPet(dogPet);
		owner1.addPet(catPet);
		
		dogVisit1 = Visit.builder().id(1l).date(LocalDate.now()).description("visitDescription1").pet(dogPet).build();
		dogVisit2 = Visit.builder().id(2l).date(LocalDate.now()).description("visitDescription2").pet(dogPet).build();
		catVisit1 = Visit.builder().id(3l).date(LocalDate.now()).description("visitDescription3").pet(catPet).build();
		catVisit2 = Visit.builder().id(4l).date(LocalDate.now()).description("visitDescription4").pet(catPet).build();
		dogPet.addVisit(dogVisit1);
		dogPet.addVisit(dogVisit2);
		catPet.addVisit(catVisit1);
		catPet.addVisit(catVisit2);
		
		
		specialty1 = Specialty.builder().id(1l).name("specialtyName1").build();
		specialty2 = Specialty.builder().id(2l).name("specialtyName2").build();
		
		vet1 = Vet.builder().id(1l).firstName("vetFirstName1").lastName("vetLastName1").build();
		vet1.addSpecialty(specialty1);
		vet1.addSpecialty(specialty2);
		
		
		
		
		//test null ids
		
		
		ownerDTOForNewPetTest = OwnerDTO.builder().id("2").address("ownerAddress2").city("ownerCity2").firstName("ownerFirstName2").lastName("ownerLastName2")
				.telephone("ownerTelephone2").build();
		ownerForNewPetTest = Owner.builder().id(2l).address("ownerAddress2").city("ownerCity2").firstName("ownerFirstName2").lastName("ownerLastName2")
				.telephone("ownerTelephone2").build();
		ownerMongoForNewPetTest = OwnerMongo.builder().id("2").address("ownerAddress2").city("ownerCity2").firstName("ownerFirstName2").lastName("ownerLastName2")
				.telephone("ownerTelephone2").build();
		
		
		
		dogPetDTOForNewVisitTest = PetDTO.builder().id("3").birthDate(LocalDate.now()).name("pet3name").petType(dogPetTypeDTO).build();
		dogPetForNewVisitTest = Pet.builder().id(3l).birthDate(LocalDate.now()).name("pet3name").petType(dogPetType).build();
		dogPetMongoForNewVisitTest = PetMongo.builder().id("3").birthDate(LocalDate.now()).name("pet3name").petType(dogPetTypeMongo).build();
		
		
		
		
		
		petTypeDTOWithNullId = new PetTypeDTO(null, "Dog");
		petDTOWithNullId = PetDTO.builder().id(null).birthDate(LocalDate.now()).name("pet1name").petType(dogPetTypeDTO).build();		
		ownerDTOWithNullId = OwnerDTO.builder().id(null).address("ownerAddress1").city("ownerCity1").firstName("ownerFirstName1").lastName("ownerLastName1")
				.telephone("ownerTelephone1").build();
		
		ownerDTOForNewPetTest.addPet(petDTOWithNullId);
		
		visitDTOWithNullId = VisitDTO.builder().id(null).date(LocalDate.now()).description("visitDescription1").pet(dogPetDTOForNewVisitTest).build();
		specialtyDTOWithNullId = SpecialtyDTO.builder().id(null).name("specialtyName1").build();
		vetDTOWithNullId = VetDTO.builder().id(null).firstName("vetFirstName1").lastName("vetLastName1").build();
		vetDTOWithNullId.addSpecialty(specialtyDTO1);
		vetDTOWithNullId.addSpecialty(specialtyDTO2);
		
		
		
		
		
		
		petTypeWithNullId = new PetType(null, "Dog");
		petWithNullId = Pet.builder().id(null).birthDate(LocalDate.now()).name("pet1name").petType(dogPetType).build();
		ownerWithNullId = Owner.builder().id(null).address("ownerAddress1").city("ownerCity1").firstName("ownerFirstName1").lastName("ownerLastName1")
				.telephone("ownerTelephone1").build();
		
		ownerForNewPetTest.addPet(petWithNullId);
		
		visitWithNullId = Visit.builder().id(null).date(LocalDate.now()).description("visitDescription1").pet(dogPetForNewVisitTest).build();
		specialtyWithNullId = Specialty.builder().id(null).name("specialtyName1").build();
		vetWithNullId = Vet.builder().id(null).firstName("vetFirstName1").lastName("vetLastName1").build();
		vetWithNullId.getSpecialties().add(specialty1);
		vetWithNullId.getSpecialties().add(specialty2);
		
		
		
		
		
		
		petTypeMongoWithNullId = new PetTypeMongo(null, "Dog");
		petMongoWithNullId = PetMongo.builder().birthDate(LocalDate.now()).id(null).name("pet1name").petType(dogPetTypeMongo).owner(ownerMongoForNewPetTest.getId()).build();
		ownerMongoWithNullId = OwnerMongo.builder().id(null).address("ownerAddress1").city("ownerCity1").firstName("ownerFirstName1").lastName("ownerLastName1")
				.telephone("ownerTelephone1").build();				
		visitMongoWithNullId = VisitMongo.builder().id(null).date(LocalDate.now()).description("visitDescription1").pet(dogPetMongoForNewVisitTest.getId()).build();
		specialtyMongoWithNullId = SpecialtyMongo.builder().id(null).name("specialtyName1").build();
		vetMongoWithNullId = VetMongo.builder().id(null).firstName("vetFirstName1").lastName("vetLastName1").build();
		vetMongoWithNullId.getSpecialties().add(specialtyMongo1);
		vetMongoWithNullId.getSpecialties().add(specialtyMongo2);
		
	}
	
	private boolean equalsWithNulls(Object a, Object b) {
	    if (a==b) return true;
	    if ((a==null)||(b==null)) return false;
	    return a.equals(b);
	  }
	
	private boolean testOwnerEquals(OwnerDTO dto, Owner entity) {
		
		if(equalsWithNulls(dto.getAddress(), entity.getAddress())
			&& equalsWithNulls(dto.getCity(), entity.getCity())
			&& equalsWithNulls(dto.getFirstName(), entity.getFirstName())
			&& equalsWithNulls(dto.getLastName(), entity.getLastName())
			&& equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
			&& equalsWithNulls(dto.getTelephone(), entity.getTelephone())
				) {
			
			Set<PetDTO> dtoPets = dto.getPets();
			Set<Pet> entityPets = entity.getPets();
			
			if(dtoPets.size() == entityPets.size()) {
				
				for(PetDTO petDTO : dtoPets) {
					
					if(!testPetEquals(petDTO, entity.getPet(petDTO.getName()))) {
						
						return false;
					}
					
				}
				return true;
			}
			
			return false;
		}
		return false;
	}
		
	private boolean testPetEquals(PetDTO dto, Pet entity) {
		
		if(equalsWithNulls(dto.getBirthDate(), entity.getBirthDate())
			&& equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
			&& equalsWithNulls(dto.getName(), entity.getName())
				) {
			
			if(!testPetTypeEquals(dto.getPetType(), entity.getPetType())) {
				return false;
			}
			
			Set<VisitDTO> dtoVisits = dto.getVisits();
			Set<Visit> entityVisit = entity.getVisits();
			
			if(dtoVisits.size() == entityVisit.size()) {
				
				return true;
			}
		}
			
		return false;
	}

	private boolean testVisitEquals(VisitDTO dto, Visit entity) {
		
		if(equalsWithNulls(dto.getDescription(), entity.getDescription())
				&& equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
				&& equalsWithNulls(dto.getDate(), entity.getDate())
					) {
			return true;
		}
		
		return false;
	}
	
	private boolean testSpecialtyEquals(SpecialtyDTO dto, Specialty entity) {
		
		if(equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
			&& equalsWithNulls(dto.getName(), entity.getName())){
			
			return true;
		}
		
		return false;
	}
	
	private boolean testVetEquals(VetDTO dto, Vet entity) {
		
		if(equalsWithNulls(dto.getFirstName(), entity.getFirstName())
			&& equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
			&& equalsWithNulls(dto.getLastName(), entity.getLastName())						
			) {
			
			Set<SpecialtyDTO> dtoSpecialties = dto.getSpecialties();
			Set<Specialty> entitySpecialties = entity.getSpecialties();
			
			if(dtoSpecialties.size() == entitySpecialties.size()) {
				
				return true;
			}
			
			return false;
		}
		
		return false;
	}
	
	
	private boolean testOwnerEquals(OwnerDTO dto, OwnerMongo entity) {
		
		if(equalsWithNulls(dto.getAddress(), entity.getAddress())
			&& equalsWithNulls(dto.getCity(), entity.getCity())
			&& equalsWithNulls(dto.getFirstName(), entity.getFirstName())
			&& equalsWithNulls(dto.getLastName(), entity.getLastName())
			&& equalsWithNulls(dto.getId(), entity.getId())
			&& equalsWithNulls(dto.getTelephone(), entity.getTelephone())
				) {
			
			Set<PetDTO> dtoPets = dto.getPets();
			Set<String> entityPets = entity.getPets();
			
			if(dtoPets.size() == entityPets.size()) {
				
				
				for(PetDTO petDTO : dtoPets) {
					
					if(!entityPets.contains(petDTO.getId())) {
						
						return false;
					}
					
				}
				return true;
			}
			
			return false;
		}
		return false;
	}
		
	private boolean testPetEquals(PetDTO dto, PetMongo entity) {
		
		if(equalsWithNulls(dto.getBirthDate(), entity.getBirthDate())
			&& equalsWithNulls(dto.getId(), entity.getId())
			&& equalsWithNulls(dto.getName(), entity.getName())
				) {
			
			if(!testPetTypeEquals(dto.getPetType(), entity.getPetType())) {
				return false;
			}
			
			Set<VisitDTO> dtoVisits = dto.getVisits();
			Set<String> entityVisits = entity.getVisits();
			if(dtoVisits.size() == entityVisits.size()) {
				
				for(VisitDTO visitDTO : dtoVisits) {
					
					if(!entityVisits.contains(visitDTO.getId())) {
						
						return false;
					}
					
				}
				
				return true;
			}
		}
			
		return false;
		
	}

	private boolean testVisitEquals(VisitDTO dto, VisitMongo entity) {
		
		if(equalsWithNulls(dto.getDescription(), entity.getDescription())
				&& equalsWithNulls(dto.getId(), entity.getId())
				&& equalsWithNulls(dto.getDate(), entity.getDate())
					) {
			return true;
		}
		
		return false;
	}
	
	private boolean testSpecialtyEquals(SpecialtyDTO dto, SpecialtyMongo entity) {
		
		if(equalsWithNulls(dto.getId(), entity.getId())
			&& equalsWithNulls(dto.getName(), entity.getName())){
			
			return true;
		}
		
		return false;
	}
	
	private boolean testVetEquals(VetDTO dto, VetMongo entity) {
		
		if(equalsWithNulls(dto.getFirstName(), entity.getFirstName())
			&& equalsWithNulls(dto.getId(), entity.getId())
			&& equalsWithNulls(dto.getLastName(), entity.getLastName())						
			) {
			
			Set<SpecialtyDTO> dtoSpecialties = dto.getSpecialties();
			Set<SpecialtyMongo> entitySpecialties = entity.getSpecialties();
			
			if(dtoSpecialties.size() == entitySpecialties.size()) {
				
				return true;
			}
			
			return false;
		}
		
		return false;
	}
	
	
	private boolean testPetTypeEquals(PetTypeDTO dto, PetType entity) {
		
		if(equalsWithNulls(dto.getId(), entity.getId() == null ? null : entity.getId().toString())
				&& equalsWithNulls(dto.getName(), entity.getName())						
				) {
			return true;
		}
		
		return false;
	}
	
	private boolean testPetTypeEquals(PetTypeDTO dto, PetTypeMongo entity) {
		
		if(equalsWithNulls(dto.getId(), entity.getId())
				&& equalsWithNulls(dto.getName(), entity.getName())						
				) {
			return true;
		}
		
		return false;
	}

	@Test
	void testModelDTOConvertor() {
//		fail("Not yet implemented");
	}

	@Test
	void testConvertOwner() {
		
		OwnerDTO dest = modelDTOConvertor.convert(owner1);		
		assertTrue(testOwnerEquals(dest, owner1));
		
		dest = modelDTOConvertor.convert(ownerWithNullId);		
		assertTrue(testOwnerEquals(dest, ownerWithNullId));
	}

	@Test
	void testConvertMongoOwnerMongo() {
		
		OwnerDTO dest = modelDTOConvertor.convertMongo(ownerMongo1);		
		assertTrue(testOwnerEquals(dest, ownerMongo1));
		
		dest = modelDTOConvertor.convertMongo(ownerMongoWithNullId);		
		assertTrue(testOwnerEquals(dest, ownerMongoWithNullId));
	}

	@Test
	void testConvertOwnerDTO() {
		
		Owner dest = modelDTOConvertor.convert(ownerDTO1);		
		assertTrue(testOwnerEquals(ownerDTO1, dest));
		
		dest = modelDTOConvertor.convert(ownerDTOWithNullId);		
		assertTrue(testOwnerEquals(ownerDTOWithNullId, dest));
	}

	@Test
	void testConvertMongoOwnerDTO() {
		
		OwnerMongo dest = modelDTOConvertor.convertMongo(ownerDTO1);		
		assertTrue(testOwnerEquals(ownerDTO1, dest));
		
		dest = modelDTOConvertor.convertMongo(ownerDTOWithNullId);		
		assertTrue(testOwnerEquals(ownerDTOWithNullId, dest));
	}

	@Test
	void testConvertPet() {
		
		PetDTO dest = modelDTOConvertor.convert(dogPet);		
		assertTrue(testPetEquals(dest, dogPet));
		
		dest = modelDTOConvertor.convert(petWithNullId);		
		assertTrue(testPetEquals(dest, petWithNullId));
	}

	@Test
	void testConvertMongoPetMongo() {
		
		PetDTO dest = modelDTOConvertor.convertMongo(dogPetMongo);		
		assertTrue(testPetEquals(dest, dogPetMongo));
		
		dest = modelDTOConvertor.convertMongo(petMongoWithNullId);		
		assertTrue(testPetEquals(dest, petMongoWithNullId));
	}

	@Test
	void testConvertPetDTO() {
		
		Pet dest = modelDTOConvertor.convert(dogPetDTO);		
		assertTrue(testPetEquals(dogPetDTO, dest));
		
		dest = modelDTOConvertor.convert(petDTOWithNullId);		
		assertTrue(testPetEquals(petDTOWithNullId, dest));
	}

	@Test
	void testConvertMongoPetDTO() {
		
		PetMongo dest = modelDTOConvertor.convertMongo(dogPetDTO);		
		assertTrue(testPetEquals(dogPetDTO, dest));
		
		dest = modelDTOConvertor.convertMongo(petDTOWithNullId);		
		assertTrue(testPetEquals(petDTOWithNullId, dest));
	}

	@Test
	void testConvertPetType() {
		
		PetTypeDTO dest = modelDTOConvertor.convert(dogPetType);		
		assertTrue(testPetTypeEquals(dest, dogPetType));
		
		dest = modelDTOConvertor.convert(petTypeWithNullId);		
		assertTrue(testPetTypeEquals(dest, petTypeWithNullId));
	}

	@Test
	void testConvertMongoPetTypeMongo() {
		
		PetTypeDTO dest = modelDTOConvertor.convertMongo(dogPetTypeMongo);		
		assertTrue(testPetTypeEquals(dest, dogPetTypeMongo));
		
		dest = modelDTOConvertor.convertMongo(petTypeMongoWithNullId);		
		assertTrue(testPetTypeEquals(dest, petTypeMongoWithNullId));
	}

	@Test
	void testConvertPetTypeDTO() {

		PetType dest = modelDTOConvertor.convert(dogPetTypeDTO);		
		assertTrue(testPetTypeEquals(dogPetTypeDTO, dest));
		
		dest = modelDTOConvertor.convert(petTypeDTOWithNullId);		
		assertTrue(testPetTypeEquals(petTypeDTOWithNullId, dest));
	}

	@Test
	void testConvertMongoPetTypeDTO() {

		PetTypeMongo dest = modelDTOConvertor.convertMongo(dogPetTypeDTO);		
		assertTrue(testPetTypeEquals(dogPetTypeDTO, dest));
		
		dest = modelDTOConvertor.convertMongo(petTypeDTOWithNullId);		
		assertTrue(testPetTypeEquals(petTypeDTOWithNullId, dest));
	}

	@Test
	void testConvertSpecialty() {
		
		SpecialtyDTO dest = modelDTOConvertor.convert(specialty1);		
		assertTrue(testSpecialtyEquals(dest, specialty1));
		
		dest = modelDTOConvertor.convert(specialtyWithNullId);		
		assertTrue(testSpecialtyEquals(dest, specialtyWithNullId));
	}

	@Test
	void testConvertMongoSpecialtyMongo() {

		SpecialtyDTO dest = modelDTOConvertor.convertMongo(specialtyMongo1);		
		assertTrue(testSpecialtyEquals(dest, specialtyMongo1));
		
		dest = modelDTOConvertor.convertMongo(specialtyMongoWithNullId);		
		assertTrue(testSpecialtyEquals(dest, specialtyMongoWithNullId));
	}

	@Test
	void testConvertSpecialtyDTO() {

		Specialty dest = modelDTOConvertor.convert(specialtyDTO1);		
		assertTrue(testSpecialtyEquals(specialtyDTO1, dest));
		
		dest = modelDTOConvertor.convert(specialtyDTOWithNullId);		
		assertTrue(testSpecialtyEquals(specialtyDTOWithNullId, dest));
	}

	@Test
	void testConvertMongoSpecialtyDTO() {

		SpecialtyMongo dest = modelDTOConvertor.convertMongo(specialtyDTO1);		
		assertTrue(testSpecialtyEquals(specialtyDTO1, dest));
		
		dest = modelDTOConvertor.convertMongo(specialtyDTOWithNullId);		
		assertTrue(testSpecialtyEquals(specialtyDTOWithNullId, dest));
	}

	@Test
	void testConvertVet() {
		
		VetDTO dest = modelDTOConvertor.convert(vet1);		
		assertTrue(testVetEquals(dest, vet1));
		
		dest = modelDTOConvertor.convert(vetWithNullId);		
		assertTrue(testVetEquals(dest, vetWithNullId));
	}

	@Test
	void testConvertMongoVetMongo() {

		VetDTO dest = modelDTOConvertor.convertMongo(vetMongo1);		
		assertTrue(testVetEquals(dest, vetMongo1));
		
		dest = modelDTOConvertor.convertMongo(vetMongoWithNullId);		
		assertTrue(testVetEquals(dest, vetMongoWithNullId));
	}

	@Test
	void testConvertVetDTO() {

		Vet dest = modelDTOConvertor.convert(vetDTO1);		
		assertTrue(testVetEquals(vetDTO1, dest));
		
		dest = modelDTOConvertor.convert(vetDTOWithNullId);		
		assertTrue(testVetEquals(vetDTOWithNullId, dest));
	}

	@Test
	void testConvertMongoVetDTO() {

		VetMongo dest = modelDTOConvertor.convertMongo(vetDTO1);		
		assertTrue(testVetEquals(vetDTO1, dest));
		
		dest = modelDTOConvertor.convertMongo(vetDTOWithNullId);		
		assertTrue(testVetEquals(vetDTOWithNullId, dest));
	}

	@Test
	void testConvertVisit() {
		
		VisitDTO dest = modelDTOConvertor.convert(dogVisit1);		
		assertTrue(testVisitEquals(dest, dogVisit1));
		
		dest = modelDTOConvertor.convert(visitWithNullId);		
		assertTrue(testVisitEquals(dest, visitWithNullId));
	}

	@Test
	void testConvertMongoVisitMongo() {

		VisitDTO dest = modelDTOConvertor.convertMongo(dogVisitMongo1);		
		assertTrue(testVisitEquals(dest, dogVisitMongo1));
		
		dest = modelDTOConvertor.convertMongo(visitMongoWithNullId);		
		assertTrue(testVisitEquals(dest, visitMongoWithNullId));
	}

	@Test
	void testConvertVisitDTO() {

		Visit dest = modelDTOConvertor.convert(dogVisitDTO1);		
		assertTrue(testVisitEquals(dogVisitDTO1, dest));
		
		dest = modelDTOConvertor.convert(visitDTOWithNullId);
		assertTrue(testVisitEquals(visitDTOWithNullId, dest));
	}

	@Test
	void testConvertMongoVisitDTO() {

		VisitMongo dest = modelDTOConvertor.convertMongo(dogVisitDTO1);		
		assertTrue(testVisitEquals(dogVisitDTO1, dest));
		
		dest = modelDTOConvertor.convertMongo(visitDTOWithNullId);		
		assertTrue(testVisitEquals(visitDTOWithNullId, dest));
	}

}
