package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Pet;
import com.petclinic.repository.sdjpa.PetRepositorySDJPA;
import com.petclinic.service.PetService;

@Service
@Profile("sdjpa")
public class PetServiceSDJPA implements PetService {

    private final PetRepositorySDJPA petRepository;

    public PetServiceSDJPA(PetRepositorySDJPA petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> findAll() {
        List<Pet> pets = new ArrayList<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
