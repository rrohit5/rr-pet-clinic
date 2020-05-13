package com.petclinic.service.sdjpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petclinic.model.Pet;
import com.petclinic.repository.sdjpa.PetRepositorySDJPA;

@ExtendWith(MockitoExtension.class)
public class PetServiceTestSDJPA {

	@Mock
	PetRepositorySDJPA petRepository;

	@InjectMocks
	PetServiceSDJPA petSDJpaService;

	Pet pet1;
	Pet pet2;
	List<Pet> pets;

	@BeforeEach
	void setUp() {

		pet1 = new Pet();
		pet1.setId(1l);

		pet2 = new Pet();
		pet2.setId(2l);
		
		pets = new ArrayList<>();

		pets.add(pet1);
		pets.add(pet2);

	}

	@Test
	public void findAll() {

		when(petRepository.findAll()).thenReturn(pets);

		List<Pet> returnedPests = petSDJpaService.findAll();

		assertNotNull(returnedPests);
		assertEquals(2, returnedPests.size());
	}

	@Test
	public void findById() {

		when(petRepository.findById(anyLong())).thenReturn(Optional.of(pet1));

		Pet pet = petSDJpaService.findById(1L);

		assertNotNull(pet);
	}

	@Test
	public void save() {
		
		when(petRepository.save(any())).thenReturn(pet1);
		
		Pet pet = petSDJpaService.save(pet1);
				
		assertNotNull(pet);

        verify(petRepository).save(any());		
	}
	
    @Test
    void delete() {
    	petSDJpaService.delete(pet1);

        //default is 1 times
        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	petSDJpaService.deleteById(1L);

        verify(petRepository).deleteById(anyLong());
    }

}
