package com.petclinic.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.Visit;



class VisitMapServiceTest {

    private VisitMapService visitMapService;

    private final Long visitId = 1L;

    @BeforeEach
    void setUp() {
    	
    	Owner owner = new Owner();
        owner.setId(1l);
        owner.setLastName("lastName");
        
        Pet pet = new Pet();
        pet.setId(1l);
        pet.setOwner(owner);

        visitMapService = new VisitMapService();

        Visit visit = new Visit();
        visit.setId(visitId);
        visit.setPet(pet);
        visitMapService.save(visit);
    }

    @Test
    void findAll() {

        List<Visit> visitSet = visitMapService.findAll();

        assertEquals(1, visitSet.size());
    }

    @Test
    void findByIdExistingId() {

        Visit visit = visitMapService.findById(visitId);

        assertEquals(visitId, visit.getId());
    }

    @Test
    void findByIdNotExistingId() {

        Visit visit = visitMapService.findById(5L);

        assertNull(visit);
    }

    @Test
    void findByIdNullId() {

        Visit visit = visitMapService.findById(null);

        assertNull(visit);
    }

    @Test
    void saveExistingId() {

        Long id = 2L;
        
        Owner owner = new Owner();
        owner.setId(1l);
        owner.setLastName("lastName");
        
        Pet pet = new Pet();
        pet.setId(1l);
        pet.setOwner(owner);

        Visit visit = new Visit();
        visit.setId(id);
        visit.setPet(pet);

        Visit savedVisit = visitMapService.save(visit);

        assertEquals(id, savedVisit.getId());
    }

    @Test
    void saveDuplicateId() {

        Long id = 1L;
        
        Owner owner = new Owner();
        owner.setId(1l);
        owner.setLastName("lastName");
        
        Pet pet = new Pet();
        pet.setId(1l);
        pet.setOwner(owner);

        Visit visit = new Visit();
        visit.setId(visitId);
        visit.setPet(pet);

        Visit savedVisit = visitMapService.save(visit);

        assertEquals(id, savedVisit.getId());
        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void saveNoId() {
    	
    	Owner owner = new Owner();
        owner.setId(1l);
        owner.setLastName("lastName");
        
        Pet pet = new Pet();
        pet.setId(1l);
        pet.setOwner(owner);

        Visit visit = new Visit();
        visit.setPet(pet);

        Visit savedVisit = visitMapService.save(visit);

        assertNotNull(savedVisit);
        assertNotNull(savedVisit.getId());
        assertEquals(2, visitMapService.findAll().size());
    }

    @Test
    void deleteVisit() {

        visitMapService.delete(visitMapService.findById(visitId));

        assertEquals(0, visitMapService.findAll().size());

    }

    @Test
    void deleteWithWrongId() {

    	Visit visit = new Visit();
        visit.setId(5l);

        visitMapService.delete(visit);

        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {

        Visit visit = new Visit();

        visitMapService.delete(visit);

        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void deleteNull() {

        visitMapService.delete(null);

        assertEquals(1, visitMapService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {

        visitMapService.deleteById(visitId);

        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        visitMapService.deleteById(5L);

        assertEquals(1, visitMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        visitMapService.deleteById(null);

        assertEquals(1, visitMapService.findAll().size());
    }
}