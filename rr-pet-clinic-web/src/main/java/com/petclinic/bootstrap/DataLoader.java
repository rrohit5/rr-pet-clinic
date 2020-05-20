package com.petclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.petclinic.dto.OwnerDTO;
import com.petclinic.dto.PetDTO;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.dto.SpecialtyDTO;
import com.petclinic.dto.VetDTO;
import com.petclinic.dto.VisitDTO;
import com.petclinic.service.OwnerService;
import com.petclinic.service.PetTypeService;
import com.petclinic.service.SpecialtyService;
import com.petclinic.service.VetService;
import com.petclinic.service.VisitService;

@Component
@Profile({"h2", "mongo"})
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	private final VisitService visitService;


	// @Autowired - not required for const DI
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService
			, SpecialtyService specialtyService, VisitService visitService) {
		this.visitService = visitService;
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
	}

	@Override
	public void run(String... args) throws Exception {

		int count = petTypeService.findAll().size();

        if (count == 0 ){
            loadData();
        }

	}
	
	private void loadData() {
		
		PetTypeDTO dog = new PetTypeDTO();
		dog.setName("Dog");
		PetTypeDTO savedDogPetType = petTypeService.save(dog);

		PetTypeDTO cat = new PetTypeDTO();
		cat.setName("Cat");
		PetTypeDTO savedCatPetType = petTypeService.save(cat);
		
		SpecialtyDTO radiology = new SpecialtyDTO();
        radiology.setName("Radiology");
        SpecialtyDTO savedRadiology = specialtyService.save(radiology);

        SpecialtyDTO surgery = new SpecialtyDTO();
        surgery.setName("Surgery");
        SpecialtyDTO savedSurgery = specialtyService.save(surgery);

        SpecialtyDTO dentistry = new SpecialtyDTO();
        dentistry.setName("Dentistry");
        SpecialtyDTO savedDentistry = specialtyService.save(dentistry);

		OwnerDTO owner1 = new OwnerDTO();
		// remove ID parameter as we are generating it automatically
//	        owner1.setId(1L);
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");
        
        PetDTO mikesPet = new PetDTO();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

		ownerService.save(owner1);

		OwnerDTO owner2 = new OwnerDTO();
		// remove ID parameter as we are generating it automatically
//	        owner2.setId(2L);
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");
		owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231234");

        PetDTO fionasCat = new PetDTO();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionasCat);

		OwnerDTO ownerSaved =  ownerService.save(owner2);
		
		System.out.println("Loaded Owners....");

		VetDTO vet1 = new VetDTO();
		// remove ID parameter as we are generating it automatically
//	        vet1.setId(1L);
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		vet1.getSpecialties().add(savedRadiology);

		vetService.save(vet1);

		VetDTO vet2 = new VetDTO();
		// remove ID parameter as we are generating it automatically
//	        vet2.setId(2L);
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		vet2.getSpecialties().add(savedSurgery);

		vetService.save(vet2);
		
		VetDTO vet3 = new VetDTO();
		// remove ID parameter as we are generating it automatically
//	        vet2.setId(2L);
		vet3.setFirstName("Jacky");
		vet3.setLastName("Chan");
		vet3.getSpecialties().add(savedDentistry);

		vetService.save(vet3);

		System.out.println("Loaded Vets....");
		
		VisitDTO catVisit = new VisitDTO();
        catVisit.setPet(ownerSaved.getPet("Just Cat"));
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

//        visitService.save(catVisit);

	}

}
