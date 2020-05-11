package com.petclinic.service.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petclinic.model.Pet;
import com.petclinic.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
public class PetSDJpaServiceTest {

	@Mock
	PetRepository petRepository;

	@InjectMocks
	PetSDJpaService petSDJpaService;

	Pet pet1;
	Pet pet2;
	Set<Pet> pets;

	@BeforeEach
	void setUp() {

		pet1 = new Pet();
		pet1.setId(1l);

		pet2 = new Pet();
		pet2.setId(2l);
		
		pets = new HashSet<>();

		pets.add(pet1);
		pets.add(pet2);

	}

	@Test
	public void findAll() {

		when(petRepository.findAll()).thenReturn(pets);

		Set<Pet> returnedPests = petSDJpaService.findAll();

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
				
		assertNotNull(pet1);

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
