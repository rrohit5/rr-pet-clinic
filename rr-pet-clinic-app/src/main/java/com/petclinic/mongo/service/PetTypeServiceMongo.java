package com.petclinic.mongo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.PetTypeDTO;
import com.petclinic.mongo.model.PetTypeMongo;
import com.petclinic.mongo.repository.PetTypeRepositoryMongo;
import com.petclinic.service.PetTypeService;

@Service
@Profile({"mongo"})
public class PetTypeServiceMongo implements PetTypeService {

    private final PetTypeRepositoryMongo petTypeRepository;
    private ModelDTOConvertor convertor;

    public PetTypeServiceMongo(PetTypeRepositoryMongo petTypeRepository
    		, ModelDTOConvertor convertor) {
        this.petTypeRepository = petTypeRepository;
        this.convertor = convertor;
    }

    @Override
    public List<PetTypeDTO> findAll() {
    	
        List<PetTypeMongo> petTypes = new ArrayList<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        
        return petTypes.stream().map((p) -> convertor.convertMongo(p))
        		.collect(Collectors.toList());
    }

    @Override
    public PetTypeDTO findById(String id) {
    	
    	PetTypeMongo petType = petTypeRepository.findById(id).orElse(null);
    	
    	return convertor.convertMongo(petType);
    }

    @Override
    public PetTypeDTO save(PetTypeDTO petTypeDTO) {
    	
    	PetTypeMongo petType = petTypeRepository.save(convertor.convertMongo(petTypeDTO));
    	
        return convertor.convertMongo(petType);
    }

    @Override
    public void delete(PetTypeDTO petTypeDTO) {
    	
    	petTypeRepository.delete(convertor.convertMongo(petTypeDTO));
    }

    @Override
    public void deleteById(String id) {
    	
        petTypeRepository.deleteById(id);
    }
}
