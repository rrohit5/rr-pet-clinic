package com.petclinic.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petclinic.model.Specialty;



class SpecialtyMapServiceTest {

    private SpecialtyMapService specialtyMapService;

    private final Long specialtyId = 1L;

    @BeforeEach
    void setUp() {

        specialtyMapService = new SpecialtyMapService();

        Specialty specialty = new Specialty();
        specialty.setId(specialtyId);
        specialtyMapService.save(specialty);
    }

    @Test
    void findAll() {

        Set<Specialty> specialtySet = specialtyMapService.findAll();

        assertEquals(1, specialtySet.size());
    }

    @Test
    void findByIdExistingId() {

        Specialty specialty = specialtyMapService.findById(specialtyId);

        assertEquals(specialtyId, specialty.getId());
    }

    @Test
    void findByIdNotExistingId() {

        Specialty specialty = specialtyMapService.findById(5L);

        assertNull(specialty);
    }

    @Test
    void findByIdNullId() {

        Specialty specialty = specialtyMapService.findById(null);

        assertNull(specialty);
    }

    @Test
    void saveExistingId() {

        Long id = 2L;

        Specialty specialty = new Specialty();
        specialty.setId(id);

        Specialty savedSpecialty = specialtyMapService.save(specialty);

        assertEquals(id, savedSpecialty.getId());
    }

    @Test
    void saveDuplicateId() {

        Long id = 1L;

        Specialty specialty = new Specialty();
        specialty.setId(specialtyId);

        Specialty savedSpecialty = specialtyMapService.save(specialty);

//        assertEquals(id, savedSpecialty.getId());
        assertEquals(1, specialtyMapService.findAll().size());
    }

    @Test
    void saveNoId() {

        Specialty savedSpecialty = specialtyMapService.save(new Specialty());

        assertNotNull(savedSpecialty);
        assertNotNull(savedSpecialty.getId());
        assertEquals(2, specialtyMapService.findAll().size());
    }

    @Test
    void deleteSpecialty() {

        specialtyMapService.delete(specialtyMapService.findById(specialtyId));

        assertEquals(0, specialtyMapService.findAll().size());

    }

    @Test
    void deleteWithWrongId() {

    	Specialty specialty = new Specialty();
        specialty.setId(5l);

        specialtyMapService.delete(specialty);

        assertEquals(1, specialtyMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {

        Specialty specialty = new Specialty();

        specialtyMapService.delete(specialty);

        assertEquals(1, specialtyMapService.findAll().size());
    }

    @Test
    void deleteNull() {

        specialtyMapService.delete(null);

        assertEquals(1, specialtyMapService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {

        specialtyMapService.deleteById(specialtyId);

        assertEquals(0, specialtyMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        specialtyMapService.deleteById(5L);

        assertEquals(1, specialtyMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        specialtyMapService.deleteById(null);

        assertEquals(1, specialtyMapService.findAll().size());
    }
}