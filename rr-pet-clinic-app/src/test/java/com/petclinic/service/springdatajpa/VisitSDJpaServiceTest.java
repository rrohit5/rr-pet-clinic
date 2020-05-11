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

import com.petclinic.model.Visit;
import com.petclinic.repository.VisitRepository;

@ExtendWith(MockitoExtension.class)
public class VisitSDJpaServiceTest {

	@Mock
	VisitRepository repository;

	@InjectMocks
	VisitSDJpaService service;

	Visit visit1;
	Visit visit2;
	Set<Visit> sps;

	@BeforeEach
	void setUp() {

		visit1 = new Visit();
		visit1.setId(1l);

		visit2 = new Visit();
		visit2.setId(2l);

		sps = new HashSet<>();
		sps.add(visit1);
		sps.add(visit2);

	}

	@Test
	public void findAll() {

		when(repository.findAll()).thenReturn(sps);

		Set<Visit> returnedsps = service.findAll();

		assertNotNull(returnedsps);
		assertEquals(2, returnedsps.size());
	}

	@Test
	public void findById() {

		when(repository.findById(anyLong())).thenReturn(Optional.of(visit1));

		Visit sp = service.findById(1L);

		assertNotNull(sp);
	}

	@Test
	public void save() {
		
		when(repository.save(any())).thenReturn(visit1);
		
		Visit sp = service.save(visit1);
				
		assertNotNull(visit1);

        verify(repository).save(any());		
	}
	
    @Test
    void delete() {
    	service.delete(visit1);

        //default is 1 times
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	service.deleteById(1L);

        verify(repository).deleteById(anyLong());
    }

}
