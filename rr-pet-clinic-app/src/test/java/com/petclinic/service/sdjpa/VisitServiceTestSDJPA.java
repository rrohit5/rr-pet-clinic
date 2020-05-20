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
import com.petclinic.dto.VisitDTO;
import com.petclinic.model.Pet;
import com.petclinic.model.Visit;
import com.petclinic.repository.sdjpa.VisitRepositorySDJPA;

@ExtendWith(MockitoExtension.class)
public class VisitServiceTestSDJPA {

	@Mock
	VisitRepositorySDJPA repository;
	
	@Mock
    private ModelDTOConvertor convertor1;
    
    private ModelDTOConvertor convertor;

	@InjectMocks
	VisitServiceSDJPA service;

	Visit visit1;
	Visit visit2;
		
	VisitDTO visitDTO1;
	VisitDTO visitDTO2;

	@BeforeEach
	void setUp() {
		
		convertor = new ModelDTOConvertor();

		visit1 = Visit.builder().id(1l).date(LocalDate.now()).description("visitDescription1").pet(Pet.builder().id(1l).build()).build();
		visit2 = Visit.builder().id(2l).date(LocalDate.now()).description("visitDescription2").pet(Pet.builder().id(1l).build()).build();
		
		visitDTO1 = convertor.convert(visit1);
		visitDTO2 = convertor.convert(visit2);
	}

	@Test
	public void findAll() {
		
		List<Visit> visits = new ArrayList<>();
		visits.add(visit1);
		visits.add(visit2);
		
		when(convertor1.convert(any(Visit.class))).thenReturn(visitDTO1);

		when(repository.findAll()).thenReturn(visits);

		List<VisitDTO> returnedsps = service.findAll();

		assertNotNull(returnedsps);
		assertEquals(2, returnedsps.size());
	}

	@Test
	public void findById() {

		when(convertor1.convert(any(Visit.class))).thenReturn(visitDTO1);
		
		when(repository.findById(anyLong())).thenReturn(Optional.of(visit1));

		VisitDTO visit = service.findById("1");

		assertNotNull(visit);
	}

	@Test
	public void save() {
		
		when(convertor1.convert(any(VisitDTO.class))).thenReturn(visit1);
		
		when(convertor1.convert(any(Visit.class))).thenReturn(visitDTO1);
		
		when(repository.save(any())).thenReturn(visit1);
		
		VisitDTO visit = service.save(visitDTO1);
				
		assertNotNull(visit);

        verify(repository).save(any());		
	}
	
    @Test
    void delete() {
    	
    	when(convertor1.convert(any(VisitDTO.class))).thenReturn(visit1);
    	
    	service.delete(visitDTO1);

        //default is 1 times
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	
    	service.deleteById("1");

        verify(repository).deleteById(anyLong());
    }

}
