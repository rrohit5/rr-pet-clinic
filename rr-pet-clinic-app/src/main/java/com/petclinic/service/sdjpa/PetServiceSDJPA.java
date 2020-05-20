package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.PetDTO;
import com.petclinic.model.Pet;
import com.petclinic.repository.sdjpa.PetRepositorySDJPA;
import com.petclinic.service.PetService;

@Service
@Profile("sdjpa")
public class PetServiceSDJPA implements PetService {

    private final PetRepositorySDJPA petRepository;
    private ModelDTOConvertor convertor;

    public PetServiceSDJPA(PetRepositorySDJPA petRepository
    				, ModelDTOConvertor convertor) {
        this.petRepository = petRepository;
        this.convertor = convertor;
    }

    @Override
    public List<PetDTO> findAll() {
    	
        List<Pet> pets = new ArrayList<>();
        petRepository.findAll().forEach(pets::add);
        
        return pets.stream().map((p) -> convertor.convert(p)).collect(Collectors.toList());
    }

    @Override
    public PetDTO findById(String id) {
    	
    	Pet pet = petRepository.findById(Long.valueOf(id)).orElse(null);
    	
        return convertor.convert(pet);
    }

    @Override
    public PetDTO save(PetDTO perDTO) {
    	
    	Pet pet = petRepository.save(convertor.convert(perDTO));
    	
    	return convertor.convert(pet);
    }

    @Override
    public void delete(PetDTO perDTO) {
    	
        petRepository.delete(convertor.convert(perDTO));
    }

    @Override
    public void deleteById(String id) {
    	
        petRepository.deleteById(Long.valueOf(id));
    }
}
