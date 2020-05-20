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
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.model.PetType;
import com.petclinic.repository.sdjpa.PetTypeRepositorySDJPA;

@ExtendWith(MockitoExtension.class)
public class PetTypeServiceTestSDJPA {

	@Mock
	PetTypeRepositorySDJPA repository;
	
	@Mock
    private ModelDTOConvertor convertor1;
    
    private ModelDTOConvertor convertor;

	@InjectMocks
	PetTypeServiceSDJPA service;

	PetType petType1;
	PetType petType2;
	
	PetTypeDTO petTypeDTO1;
	PetTypeDTO petTypeDTO2;

	@BeforeEach
	void setUp() {
		
		convertor = new ModelDTOConvertor();

		petType1 =  new PetType(1l, "Dog");
		petType2 = new PetType(2l, "Cat");
		
		petTypeDTO1 = convertor.convert(petType1);
		petTypeDTO2 = convertor.convert(petType2);
	}

	@Test
	public void findAll() {
		
		List<PetType> petTypes = new ArrayList<>();
		
		petTypes.add(petType1);
		petTypes.add(petType2);
		
		when(convertor1.convert(any(PetType.class))).thenReturn(petTypeDTO1);

		when(repository.findAll()).thenReturn(petTypes);

		List<PetTypeDTO> returnedPetTypes = service.findAll();

		assertNotNull(returnedPetTypes);
		assertEquals(2, returnedPetTypes.size());
	}

	@Test
	public void findById() {
		
		when(convertor1.convert(any(PetType.class))).thenReturn(petTypeDTO1);

		when(repository.findById(anyLong())).thenReturn(Optional.of(petType1));

		PetTypeDTO petType = service.findById("1");

		assertNotNull(petType);
	}

	@Test
	public void save() {
		
		when(convertor1.convert(any(PetTypeDTO.class))).thenReturn(petType1);
    	
    	when(convertor1.convert(any(PetType.class))).thenReturn(petTypeDTO1);
		
		when(repository.save(any())).thenReturn(petType1);
		
		PetTypeDTO petType = service.save(petTypeDTO1);
				
		assertNotNull(petType);

        verify(repository).save(any());		
	}
	
    @Test
    void delete() {
    	
    	when(convertor1.convert(any(PetTypeDTO.class))).thenReturn(petType1);
    	
    	service.delete(petTypeDTO1);

        //default is 1 times
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	
    	service.deleteById("1");

        verify(repository).deleteById(anyLong());
    }

}
