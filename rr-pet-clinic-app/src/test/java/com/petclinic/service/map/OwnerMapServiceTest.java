package com.petclinic.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petclinic.model.Owner;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName(lastName);
        
        ownerMapService.save(owner);
    }

    @Test
    void findAll() {
        List<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;

        Owner owner2 = new Owner();
        owner2.setId(id);

        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId());

    }

    @Test
    void saveNoId() {

        Owner savedOwner = ownerMapService.save(new Owner());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

/*    @Test
    void findByLastName() {
        List<Owner> smith = ownerMapService.findByLastName(lastName);

        assertNotNull(smith);

        assertEquals(ownerId, smith.get(0).getId());

    }

    @Test
    void findByLastNameNotFound() {
        List<Owner> smith = ownerMapService.findByLastName("foo");

//        assertNull(smith);
        assertEquals(0, smith.size());
    }*/
}