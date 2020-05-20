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
import com.petclinic.dto.SpecialtyDTO;
import com.petclinic.model.Specialty;
import com.petclinic.repository.sdjpa.SpecialtyRepositorySDJPA;

@ExtendWith(MockitoExtension.class)
public class SpecialtyServiceTestSDJPA {

	@Mock
	SpecialtyRepositorySDJPA repository;

	@InjectMocks
	SpecialtyServiceSDJPA service;
	
	@Mock
    private ModelDTOConvertor convertor1;
    
    private ModelDTOConvertor convertor;

	Specialty specialty1;
	Specialty specialty2;
	
	SpecialtyDTO specialtyDTO1;
	SpecialtyDTO specialtyDTO2;

	@BeforeEach
	void setUp() {
		
		convertor = new ModelDTOConvertor();

		specialty1 = Specialty.builder().id(1l).name("specialtyName1").build();
		specialty2 = Specialty.builder().id(2l).name("specialtyName2").build();
		
		specialtyDTO1 = convertor.convert(specialty1);
		specialtyDTO2 = convertor.convert(specialty2);
	}

	@Test
	public void findAll() {
		
		List<Specialty> specialties = new ArrayList<>();
		specialties.add(specialty1);
		specialties.add(specialty2);
		
		when(convertor1.convert(any(Specialty.class))).thenReturn(specialtyDTO1);

		when(repository.findAll()).thenReturn(specialties);

		List<SpecialtyDTO> returnedsps = service.findAll();

		assertNotNull(returnedsps);
		assertEquals(2, returnedsps.size());
	}

	@Test
	public void findById() {
		
		when(convertor1.convert(any(Specialty.class))).thenReturn(specialtyDTO1);

		when(repository.findById(anyLong())).thenReturn(Optional.of(specialty1));

		SpecialtyDTO sp = service.findById("1");

		assertNotNull(sp);
	}

	@Test
	public void save() {
		
		when(convertor1.convert(any(SpecialtyDTO.class))).thenReturn(specialty1);
		
		when(convertor1.convert(any(Specialty.class))).thenReturn(specialtyDTO1);
		
		when(repository.save(any())).thenReturn(specialty1);
		
		SpecialtyDTO sp2 = service.save(specialtyDTO1);
				
		assertNotNull(sp2);

        verify(repository).save(any());		
	}
	
    @Test
    void delete() {
    	
    	when(convertor1.convert(any(SpecialtyDTO.class))).thenReturn(specialty1);
    	
    	service.delete(specialtyDTO1);

        //default is 1 times
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	
    	service.deleteById("1");

        verify(repository).deleteById(anyLong());
    }

}
