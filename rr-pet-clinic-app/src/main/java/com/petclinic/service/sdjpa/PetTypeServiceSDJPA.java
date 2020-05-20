package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.model.PetType;
import com.petclinic.repository.sdjpa.PetTypeRepositorySDJPA;
import com.petclinic.service.PetTypeService;

@Service
@Profile("sdjpa")
public class PetTypeServiceSDJPA implements PetTypeService {

    private final PetTypeRepositorySDJPA petTypeRepository;
    private ModelDTOConvertor convertor;

    public PetTypeServiceSDJPA(PetTypeRepositorySDJPA petTypeRepository
    							, ModelDTOConvertor convertor) {
        this.petTypeRepository = petTypeRepository;
        this.convertor = convertor;
    }

    @Override
    public List<PetTypeDTO> findAll() {
    	
        List<PetType> petTypes = new ArrayList<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        
        return petTypes.stream().map((p) -> convertor.convert(p))
        		.collect(Collectors.toList());
    }

    @Override
    public PetTypeDTO findById(String id) {
    	
    	PetType petType = petTypeRepository.findById(Long.valueOf(id)).orElse(null);
    	
    	return convertor.convert(petType);
    }

    @Override
    public PetTypeDTO save(PetTypeDTO petTypeDTO) {
    	
    	PetType petType = convertor.convert(petTypeDTO);
    	petType = petTypeRepository.save(petType);
    	
        return convertor.convert(petType);
    }

    @Override
    public void delete(PetTypeDTO petTypeDTO) {
    	
    	petTypeRepository.delete(convertor.convert(petTypeDTO));
    }

    @Override
    public void deleteById(String id) {
    	
        petTypeRepository.deleteById(Long.valueOf(id));
    }
}
