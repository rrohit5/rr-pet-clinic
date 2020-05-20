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

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.VetDTO;
import com.petclinic.model.Vet;
import com.petclinic.repository.sdjpa.VetRepositorySDJPA;

@ExtendWith(MockitoExtension.class)
public class VetServiceTestSDJPA {

	@Mock
	VetRepositorySDJPA repository;

	@InjectMocks
	VetServiceSDJPA service;
	
	@Mock
    private ModelDTOConvertor convertor1;
    
    private ModelDTOConvertor convertor;

	Vet vet1;
	Vet vet2;
	
	VetDTO vetDTO1;
	VetDTO vetDTO2;
	
	@BeforeEach
	void setUp() {
		
		convertor = new ModelDTOConvertor();

		vet1 = Vet.builder().id(1l).firstName("vetFirstName1").lastName("vetLastName1").build();
		vet2 = Vet.builder().id(2l).firstName("vetFirstName2").lastName("vetLastName2").build();

		vetDTO1 = convertor.convert(vet1);
		vetDTO2 = convertor.convert(vet2);
	}

	@Test
	public void findAll() {
		
		List<Vet> vets = new ArrayList<>();
		vets.add(vet1);
		vets.add(vet2);
		
		when(convertor1.convert(any(Vet.class))).thenReturn(vetDTO1);

		when(repository.findAll()).thenReturn(vets);

		List<VetDTO> returnedvets = service.findAll();

		assertNotNull(returnedvets);
		assertEquals(2, returnedvets.size());
	}

	@Test
	public void findById() {
		
		when(convertor1.convert(any(Vet.class))).thenReturn(vetDTO1);

		when(repository.findById(anyLong())).thenReturn(Optional.of(vet1));

		VetDTO sp = service.findById("1");

		assertNotNull(sp);
	}

	@Test
	public void save() {
		
		when(convertor1.convert(any(Vet.class))).thenReturn(vetDTO1);
		
		when(convertor1.convert(any(VetDTO.class))).thenReturn(vet1);
		
		when(repository.save(any())).thenReturn(vet1);
		
		VetDTO sp = service.save(vetDTO1);
				
		assertNotNull(sp);

        verify(repository).save(any());		
	}
	
    @Test
    void delete() {
    	
    	when(convertor1.convert(any(VetDTO.class))).thenReturn(vet1);
    	
    	service.delete(vetDTO1);

        //default is 1 times
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	
    	service.deleteById("1");

        verify(repository).deleteById(anyLong());
    }

}
