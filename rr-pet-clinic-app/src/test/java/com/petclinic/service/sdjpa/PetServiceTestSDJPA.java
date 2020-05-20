package com.petclinic.service.sdjpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.PetDTO;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.repository.sdjpa.PetRepositorySDJPA;

@ExtendWith(MockitoExtension.class)
public class PetServiceTestSDJPA {

	@Mock
	PetRepositorySDJPA petRepository;
	
	@Mock
    private ModelDTOConvertor convertor1;
    
    private ModelDTOConvertor convertor;

	@InjectMocks
	PetServiceSDJPA petSDJpaService;

	Pet pet1;
	Pet pet2;
	
	PetDTO petDTO1;
	PetDTO petDTO2;

	@BeforeEach
	void setUp() {
		
		convertor = new ModelDTOConvertor();
		
		PetType dogPetType = new PetType(1l, "Dog");

		pet1 = Pet.builder().id(1l).birthDate(LocalDate.now()).name("pet1name").petType(dogPetType).build();

		pet2 = Pet.builder().id(2l).birthDate(LocalDate.now()).name("pet1name").petType(dogPetType).build();
		
		petDTO1 = convertor.convert(pet1);
		petDTO2 = convertor.convert(pet2);

	}

	@Test
	public void findAll() {
		
		List<Pet> pets = new ArrayList<>();
		pets.add(pet1);
		pets.add(pet2);
		
		when(convertor1.convert(any(Pet.class))).thenReturn(petDTO1);

		when(petRepository.findAll()).thenReturn(pets);

		List<PetDTO> returnedPests = petSDJpaService.findAll();

		assertNotNull(returnedPests);
		assertEquals(2, returnedPests.size());
	}

	@Test
	public void findById() {
		
		when(convertor1.convert(any(Pet.class))).thenReturn(petDTO1);

		when(petRepository.findById(anyLong())).thenReturn(Optional.of(pet1));

		PetDTO pet = petSDJpaService.findById("1");

		assertNotNull(pet);
	}

	@Test
	public void save() {
		
		when(convertor1.convert(any(PetDTO.class))).thenReturn(pet1);
    	
    	when(convertor1.convert(any(Pet.class))).thenReturn(petDTO1);
		
		when(petRepository.save(any())).thenReturn(pet1);
		
		PetDTO pet = petSDJpaService.save(petDTO1);
				
		assertNotNull(pet);

        verify(petRepository).save(any());		
	}
	
    @Test
    void delete() {
    	
    	when(convertor1.convert(any(PetDTO.class))).thenReturn(pet1);
    	
    	petSDJpaService.delete(petDTO1);

        //default is 1 times
        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	
    	petSDJpaService.deleteById("1");

        verify(petRepository).deleteById(anyLong());
    }

}
