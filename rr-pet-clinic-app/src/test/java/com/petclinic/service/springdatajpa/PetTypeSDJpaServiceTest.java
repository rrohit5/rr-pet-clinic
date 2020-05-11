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

import com.petclinic.model.PetType;
import com.petclinic.repository.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
public class PetTypeSDJpaServiceTest {

	@Mock
	PetTypeRepository repository;

	@InjectMocks
	PetTypeSDJpaService service;

	PetType petType1;
	PetType petType2;
	Set<PetType> petTypes;

	@BeforeEach
	void setUp() {

		petType1 = new PetType();
		petType1.setId(1l);

		petType2 = new PetType();
		petType2.setId(2l);

		petTypes = new HashSet<>();
		
		petTypes.add(petType1);
		petTypes.add(petType2);

	}

	@Test
	public void findAll() {

		when(repository.findAll()).thenReturn(petTypes);

		Set<PetType> returnedPetTypes = service.findAll();

		assertNotNull(returnedPetTypes);
		assertEquals(2, returnedPetTypes.size());
	}

	@Test
	public void findById() {

		when(repository.findById(anyLong())).thenReturn(Optional.of(petType1));

		PetType petType = service.findById(1L);

		assertNotNull(petType);
	}

	@Test
	public void save() {
		
		when(repository.save(any())).thenReturn(petType1);
		
		PetType petType = service.save(petType1);
				
		assertNotNull(petType1);

        verify(repository).save(any());		
	}
	
    @Test
    void delete() {
    	service.delete(petType1);

        //default is 1 times
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	service.deleteById(1L);

        verify(repository).deleteById(anyLong());
    }

}
