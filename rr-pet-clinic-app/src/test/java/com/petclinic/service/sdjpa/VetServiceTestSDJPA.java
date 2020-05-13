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

import com.petclinic.model.Vet;
import com.petclinic.repository.sdjpa.VetRepositorySDJPA;

@ExtendWith(MockitoExtension.class)
public class VetServiceTestSDJPA {

	@Mock
	VetRepositorySDJPA repository;

	@InjectMocks
	VetServiceSDJPA service;

	Vet vet1;
	Vet vet2;
	List<Vet> vets;

	@BeforeEach
	void setUp() {

		vet1 = new Vet();
		vet1.setId(1l);

		vet2 = new Vet();
		vet2.setId(2l);
		
		vets = new ArrayList<>();

		vets.add(vet1);
		vets.add(vet2);

	}

	@Test
	public void findAll() {

		when(repository.findAll()).thenReturn(vets);

		List<Vet> returnedvets = service.findAll();

		assertNotNull(returnedvets);
		assertEquals(2, returnedvets.size());
	}

	@Test
	public void findById() {

		when(repository.findById(anyLong())).thenReturn(Optional.of(vet1));

		Vet sp = service.findById(1L);

		assertNotNull(sp);
	}

	@Test
	public void save() {
		
		when(repository.save(any())).thenReturn(vet1);
		
		Vet sp = service.save(vet1);
				
		assertNotNull(sp);

        verify(repository).save(any());		
	}
	
    @Test
    void delete() {
    	service.delete(vet1);

        //default is 1 times
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	service.deleteById(1L);

        verify(repository).deleteById(anyLong());
    }

}
