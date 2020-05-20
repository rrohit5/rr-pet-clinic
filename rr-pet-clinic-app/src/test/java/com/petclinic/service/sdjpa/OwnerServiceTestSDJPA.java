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

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.model.Owner;
import com.petclinic.repository.sdjpa.OwnerRepositorySDJPA;
import com.petclinic.repository.sdjpa.PetRepositorySDJPA;
import com.petclinic.repository.sdjpa.PetTypeRepositorySDJPA;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTestSDJPA {

    public static final String LAST_NAME = "ownerLastName1";
    @Mock
    OwnerRepositorySDJPA ownerRepository;

    @Mock
    PetRepositorySDJPA petRepository;

    @Mock
    PetTypeRepositorySDJPA petTypeRepository;
    
    @Mock
    private ModelDTOConvertor convertor1;
    
    private ModelDTOConvertor convertor;

    @InjectMocks
    OwnerServiceSDJPA service;

    Owner owner1;
    Owner owner2;
    
    OwnerDTO ownerDTO1;
    OwnerDTO ownerDTO2;

    @BeforeEach
    void setUp() {
    	
    	convertor = new ModelDTOConvertor();
    	
    	owner1 = Owner.builder().id(1l).address("ownerAddress1").city("ownerCity1").firstName("ownerFirstName1").lastName("ownerLastName1")
				.telephone("ownerTelephone1").build();
    	
    	owner2 = Owner.builder().id(2l).address("ownerAddress2").city("ownerCity2").firstName("ownerFirstName2").lastName("ownerLastName2")
				.telephone("ownerTelephone2").build();
        
    	ownerDTO1 = convertor.convert(owner1);
    	ownerDTO2 = convertor.convert(owner2);
    	
    	
//    	removing mocks from here to local methods
    	
//    	when(convertor1.convert(any(OwnerDTO.class))).thenReturn(owner1);
		/*
		  org.mockito.exceptions.misusing.UnnecessaryStubbingException: Unnecessary
		  stubbings detected. Clean & maintainable test code requires zero unnecessary
		  code.
		 */
    	//when(convertor1.convert(any(Owner.class))).thenReturn(ownerDTO1);
    	
    }

    @Test
    void findByLastName() {
    	
    	when(convertor1.convert(any(Owner.class))).thenReturn(ownerDTO1);
    	
        when(ownerRepository.findByLastName(any())).thenReturn(owner1);
        
        OwnerDTO ownerDTO = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, ownerDTO.getLastName());

        verify(ownerRepository).findByLastName(any());
    }
    
    @Test
    void findAllByLastNameLike() {
    	
    	List<Owner> ownerList = new ArrayList<Owner>();
    	ownerList.add(owner1);
    	
    	when(convertor1.convert(any(Owner.class))).thenReturn(ownerDTO1);
    	
        when(ownerRepository.findAllByLastNameLike(any())).thenReturn(ownerList);

        List<OwnerDTO> owners = service.findAllByLastNameLike(LAST_NAME);
        
        OwnerDTO ownerDTO = owners.get(0);

        assertEquals(LAST_NAME, ownerDTO.getLastName());

        verify(ownerRepository).findAllByLastNameLike(any());
    }

    @Test
    void findAll() {
        
    	List<Owner> ownerList = new ArrayList<>();
    	ownerList.add(owner1);
        ownerList.add(owner2);

        when(convertor1.convert(any(Owner.class))).thenReturn(ownerDTO1);
        
        when(ownerRepository.findAll()).thenReturn(ownerList);

        List<OwnerDTO> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
    	
    	when(convertor1.convert(any(Owner.class))).thenReturn(ownerDTO1);
    	
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner1));

        OwnerDTO owner = service.findById("1");

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
    	
//    	when(convertor1.convert(any(null))).thenReturn(null);
    	
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        OwnerDTO owner = service.findById("1");

        assertNull(owner);
    }


    @Test
    void save() {
    	
    	when(convertor1.convert(any(OwnerDTO.class))).thenReturn(owner1);
    	
    	when(convertor1.convert(any(Owner.class))).thenReturn(ownerDTO1);
    	
    	when(ownerRepository.save(any())).thenReturn(owner1);

        OwnerDTO savedOwner = service.save(ownerDTO1);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
    	
    	when(convertor1.convert(any(OwnerDTO.class))).thenReturn(owner1);
    	
    	service.delete(ownerDTO1);

        //default is 1 times
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
    	
        service.deleteById("1");

        verify(ownerRepository).deleteById(anyLong());
    }
}