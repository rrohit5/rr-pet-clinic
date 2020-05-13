package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.PetType;
import com.petclinic.repository.sdjpa.PetTypeRepositorySDJPA;
import com.petclinic.service.PetTypeService;

@Service
@Profile("sdjpa")
public class PetTypeServiceSDJPA implements PetTypeService {

    private final PetTypeRepositorySDJPA petTypeRepository;

    public PetTypeServiceSDJPA(PetTypeRepositorySDJPA petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public List<PetType> findAll() {
        List<PetType> petTypes = new ArrayList<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);
    }
}
