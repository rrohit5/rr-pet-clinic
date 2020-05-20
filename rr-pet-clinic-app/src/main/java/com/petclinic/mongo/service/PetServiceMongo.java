package com.petclinic.mongo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.dto.PetDTO;
import com.petclinic.mongo.model.OwnerMongo;
import com.petclinic.mongo.model.PetMongo;
import com.petclinic.mongo.repository.OwnerRepositoryMongo;
import com.petclinic.mongo.repository.PetRepositoryMongo;
import com.petclinic.service.PetService;

@Service
@Profile({"mongo"})
public class PetServiceMongo implements PetService {

    private final PetRepositoryMongo petRepositoryMongo;
    private final OwnerRepositoryMongo ownerRepositoryMongo;
    private ModelDTOConvertor convertor;

    public PetServiceMongo(PetRepositoryMongo petRepositoryMongo, OwnerRepositoryMongo ownerRepositoryMongo
    				, ModelDTOConvertor convertor) {
        this.petRepositoryMongo = petRepositoryMongo;
        this.ownerRepositoryMongo = ownerRepositoryMongo;
        this.convertor = convertor;
    }

    @Override
    public List<PetDTO> findAll() {
    	
        List<PetMongo> pets = new ArrayList<>();
        petRepositoryMongo.findAll().forEach(pets::add);
        
        return pets.stream().map((p) -> convertor.convertMongo(p)).collect(Collectors.toList());
    	
    }

    @Override
    public PetDTO findById(String id) {
    	
    	PetMongo pet = petRepositoryMongo.findById(id).orElse(null);
    	
        return addOwnerToPet(pet);
    }

    @Override
    @Transactional
    public PetDTO save(PetDTO petDTO) {
    	
    	String id = petDTO.getId();
    	
    	OwnerDTO ownerDTO = petDTO.getOwner(); 
    	
    	if(id != null && id.isEmpty()) {
    		petDTO.setId(null);
    	}
    	
    	PetMongo pet = convertor.convertMongo(petDTO);
    	
    	pet = petRepositoryMongo.save(pet);
    	petDTO = convertor.convertMongo(pet);
    	
    	ownerDTO.getPet(pet.getName()).setId(pet.getId());
    	
    	OwnerMongo toSave = convertor.convertMongo(ownerDTO);
    	toSave = ownerRepositoryMongo.save(toSave);
    	
    	
    	
    	return petDTO;
    }

    @Override
    public void delete(PetDTO perDTO) {
    	
        petRepositoryMongo.delete(convertor.convertMongo(perDTO));
    }

    @Override
    public void deleteById(String id) {
    	
        petRepositoryMongo.deleteById(id);
    }
    
    private PetDTO addOwnerToPet(PetMongo petMongo) {
    	
    	String ownerId = petMongo.getOwner();
    	PetDTO petDTO = convertor.convertMongo(petMongo);
    	
    	if(ownerId != null && !ownerId.isEmpty()) {
    		
    		OwnerMongo owner = ownerRepositoryMongo.findById(ownerId).orElse(null);
    		OwnerDTO ownerDTO = convertor.convertMongo(owner);
    		petDTO.setOwner(ownerDTO);
    	}
    	
		return petDTO;    	
    }
}
