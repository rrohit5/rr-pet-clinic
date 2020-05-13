package com.petclinic.service.sdjpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.petclinic.model.Owner;
import com.petclinic.repository.sdjpa.OwnerRepositorySDJPA;
import com.petclinic.repository.sdjpa.PetRepositorySDJPA;
import com.petclinic.repository.sdjpa.PetTypeRepositorySDJPA;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTestSDJPA {

    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepositorySDJPA ownerRepository;

    @Mock
    PetRepositorySDJPA petRepository;

    @Mock
    PetTypeRepositorySDJPA petTypeRepository;

    @InjectMocks
    OwnerServiceSDJPA service;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
    	
    	Owner owner = new Owner();
        owner.setId(1l);
        owner.setLastName(LAST_NAME);
        
        returnOwner = owner;
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }
    
    @Test
    void findAllByLastNameLike() {
    	
    	List<Owner> returnOwnersList = new ArrayList<Owner>();
    	returnOwnersList.add(returnOwner);
    	
        when(ownerRepository.findAllByLastNameLike(any())).thenReturn(returnOwnersList);

        List<Owner> owners = service.findAllByLastNameLike(LAST_NAME);
        
        Owner smith = owners.get(0);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findAllByLastNameLike(any());
    }

    @Test
    void findAll() {
        
    	List<Owner> returnOwnersList = new ArrayList<>();
    	
    	Owner owner = new Owner();
        owner.setId(1l);        
        returnOwnersList.add(owner);
        
        Owner owner2 = new Owner();
        owner2.setId(2l);
        returnOwnersList.add(owner2);

        when(ownerRepository.findAll()).thenReturn(returnOwnersList);

        List<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }


    @Test
    void save() {
    	
    	Owner owner = new Owner();
        owner.setId(1l);
        
        Owner ownerToSave = owner;

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        //default is 1 times
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }
}