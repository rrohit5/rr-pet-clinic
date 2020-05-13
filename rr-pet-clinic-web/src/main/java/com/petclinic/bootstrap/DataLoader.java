package com.petclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.model.Specialty;
import com.petclinic.model.Vet;
import com.petclinic.model.Visit;
import com.petclinic.service.OwnerService;
import com.petclinic.service.PetTypeService;
import com.petclinic.service.SpecialtyService;
import com.petclinic.service.VetService;
import com.petclinic.service.VisitService;

@Component
@Profile({"h2"})
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
		
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Specialty radiology = new Specialty();
        radiology.setName("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setName("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setName("dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

		Owner owner1 = new Owner();
		// remove ID parameter as we are generating it automatically
//	        owner1.setId(1L);
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");
        
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

		ownerService.save(owner1);

		Owner owner2 = new Owner();
		// remove ID parameter as we are generating it automatically
//	        owner2.setId(2L);
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");
		owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231234");

        Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionasCat);

		ownerService.save(owner2);
		
		Visit catVisit = new Visit();
        catVisit.setPet(fionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

		System.out.println("Loaded Owners....");

		Vet vet1 = new Vet();
		// remove ID parameter as we are generating it automatically
//	        vet1.setId(1L);
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		vet1.getSpecialties().add(savedRadiology);

		vetService.save(vet1);

		Vet vet2 = new Vet();
		// remove ID parameter as we are generating it automatically
//	        vet2.setId(2L);
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		vet2.getSpecialties().add(savedSurgery);

		vetService.save(vet2);
		
		Vet vet3 = new Vet();
		// remove ID parameter as we are generating it automatically
//	        vet2.setId(2L);
		vet3.setFirstName("Jacky");
		vet3.setLastName("Chan");
		vet3.getSpecialties().add(savedDentistry);

		vetService.save(vet3);

		System.out.println("Loaded Vets....");
	}

}
