package com.petclinic.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petclinic.model.Vet;



class VetMapServiceTest {

    private VetMapService vetMapService;

    private final Long vetId = 1L;

    @BeforeEach
    void setUp() {

        vetMapService = new VetMapService(new SpecialtyMapService());

        Vet vet = new Vet();
        vet.setId(vetId);
        vetMapService.save(vet);
    }

    @Test
    void findAll() {

        List<Vet> vetSet = vetMapService.findAll();

        assertEquals(1, vetSet.size());
    }

    @Test
    void findByIdExistingId() {

        Vet vet = vetMapService.findById(vetId);

        assertEquals(vetId, vet.getId());
    }

    @Test
    void findByIdNotExistingId() {

        Vet vet = vetMapService.findById(5L);

        assertNull(vet);
    }

    @Test
    void findByIdNullId() {

        Vet vet = vetMapService.findById(null);

        assertNull(vet);
    }

    @Test
    void saveExistingId() {

        Long id = 2L;

        Vet vet = new Vet();
        vet.setId(id);

        Vet savedVet = vetMapService.save(vet);

        assertEquals(id, savedVet.getId());
    }

    @Test
    void saveDuplicateId() {

        Long id = 1L;

        Vet vet = new Vet();
        vet.setId(vetId);

        Vet savedVet = vetMapService.save(vet);

        assertEquals(id, savedVet.getId());
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void saveNoId() {

        Vet savedVet = vetMapService.save(new Vet());

        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
        assertEquals(2, vetMapService.findAll().size());
    }

    @Test
    void deleteVet() {

        vetMapService.delete(vetMapService.findById(vetId));

        assertEquals(0, vetMapService.findAll().size());

    }

    @Test
    void deleteWithWrongId() {

    	Vet vet = new Vet();
        vet.setId(5l);

        vetMapService.delete(vet);

        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {

        Vet vet = new Vet();

        vetMapService.delete(vet);

        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void deleteNull() {

        vetMapService.delete(null);

        assertEquals(1, vetMapService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {

        vetMapService.deleteById(vetId);

        assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        vetMapService.deleteById(5L);

        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        vetMapService.deleteById(null);

        assertEquals(1, vetMapService.findAll().size());
    }
}