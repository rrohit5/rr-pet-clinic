package com.petclinic.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petclinic.model.PetType;



public class PetTypeMapServiceTest {

    private PetTypeMapService petTypeMapService;

    private final Long petTypeId = 1L;

    @BeforeEach
    void setUp() {

        petTypeMapService = new PetTypeMapService();

        PetType petType = new PetType();
        petType.setId(petTypeId);
        petTypeMapService.save(petType);
    }

    @Test
    void findAll() {

        Set<PetType> petTypeSet = petTypeMapService.findAll();

        assertEquals(1, petTypeSet.size());
    }

    @Test
    void findByIdExistingId() {

        PetType petType = petTypeMapService.findById(petTypeId);

        assertEquals(petTypeId, petType.getId());
    }

    @Test
    void findByIdNotExistingId() {

        PetType petType = petTypeMapService.findById(5L);

        assertNull(petType);
    }

    @Test
    void findByIdNullId() {

        PetType petType = petTypeMapService.findById(null);

        assertNull(petType);
    }

    @Test
    void saveExistingId() {

        Long id = 2L;

        PetType petType = new PetType();
        petType.setId(id);

        PetType savedPetType = petTypeMapService.save(petType);

        assertEquals(id, savedPetType.getId());
    }

    @Test
    void saveDuplicateId() {

        Long id = 1L;

        PetType petType = new PetType();
        petType.setId(petTypeId);

        PetType savedPetType = petTypeMapService.save(petType);

//        assertEquals(id, savedPetType.getId());
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void saveNoId() {

        PetType savedPetType = petTypeMapService.save(new PetType());

        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
        assertEquals(2, petTypeMapService.findAll().size());
    }

    @Test
    void deletePetType() {

        petTypeMapService.delete(petTypeMapService.findById(petTypeId));

        assertEquals(0, petTypeMapService.findAll().size());

    }

    @Test
    void deleteWithWrongId() {

    	PetType petType = new PetType();
        petType.setId(5l);

        petTypeMapService.delete(petType);

        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {

        PetType petType = new PetType();

        petTypeMapService.delete(petType);

        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deleteNull() {

        petTypeMapService.delete(null);

        assertEquals(1, petTypeMapService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {

        petTypeMapService.deleteById(petTypeId);

        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        petTypeMapService.deleteById(5L);

        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        petTypeMapService.deleteById(null);

        assertEquals(1, petTypeMapService.findAll().size());
    }
}