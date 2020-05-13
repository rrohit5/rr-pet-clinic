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

import com.petclinic.model.Specialty;
import com.petclinic.repository.sdjpa.SpecialtyRepositorySDJPA;

@ExtendWith(MockitoExtension.class)
public class SpecialtyServiceTestSDJPA {

	@Mock
	SpecialtyRepositorySDJPA repository;

	@InjectMocks
	SpecialtyServiceSDJPA service;

	Specialty sp1;
	Specialty sp2;
	List<Specialty> sps;

	@BeforeEach
	void setUp() {

		sp1 = new Specialty();
		sp1.setId(1l);

		sp2 = new Specialty();
		sp2.setId(2l);

		sps = new ArrayList<>();
		sps.add(sp1);
		sps.add(sp2);

	}

	@Test
	public void findAll() {

		when(repository.findAll()).thenReturn(sps);

		List<Specialty> returnedsps = service.findAll();

		assertNotNull(returnedsps);
		assertEquals(2, returnedsps.size());
	}

	@Test
	public void findById() {

		when(repository.findById(anyLong())).thenReturn(Optional.of(sp1));

		Specialty sp = service.findById(1L);

		assertNotNull(sp);
	}

	@Test
	public void save() {
		
		when(repository.save(any())).thenReturn(sp1);
		
		Specialty sp = service.save(sp1);
				
		assertNotNull(sp);

        verify(repository).save(any());		
	}
	
    @Test
    void delete() {
    	service.delete(sp1);

        //default is 1 times
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	service.deleteById(1L);

        verify(repository).deleteById(anyLong());
    }

}