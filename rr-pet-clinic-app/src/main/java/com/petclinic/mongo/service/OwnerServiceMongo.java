package com.petclinic.mongo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.dto.PetDTO;
import com.petclinic.mongo.model.OwnerMongo;
import com.petclinic.mongo.model.PetMongo;
import com.petclinic.mongo.repository.OwnerRepositoryMongo;
import com.petclinic.mongo.repository.PetRepositoryMongo;
import com.petclinic.service.OwnerService;

@Service
@Profile({"mongo"})
public class OwnerServiceMongo implements OwnerService {

    private final OwnerRepositoryMongo ownerRepository;
    private final PetRepositoryMongo petRepository;
    
    private ModelDTOConvertor convertor;

    public OwnerServiceMongo(OwnerRepositoryMongo ownerRepository, PetRepositoryMongo petRepository
						, ModelDTOConvertor convertor) {
        this.petRepository = petRepository;
		this.ownerRepository = ownerRepository;
    	this.convertor = convertor;
    }

    @Override
    public OwnerDTO findByLastName(String lastName) {
    	
    	OwnerMongo owner = ownerRepository.findByLastName(lastName);
    	
    	OwnerDTO dto = convertor.convertMongo(owner); 
    	
        return dto;
    }

    @Override
    public List<OwnerDTO> findAllByLastNameLike(String lastName) {
    	
    	List <OwnerMongo> list = ownerRepository.findAllByLastNameLike(lastName);
    	
    	List<OwnerDTO> ret = list.stream().map((p) -> convertor.convertMongo(p)).collect(Collectors.toList());
    	
        return ret;
        		
    }

    @Override
    public List<OwnerDTO> findAll() {
    	
        List<OwnerMongo> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);
        
        List<OwnerDTO> list = owners.stream().map((p) -> convertor.convertMongo(p))
        		.collect(Collectors.toList()); 
        
        return list;
    }

    @Override
    public OwnerDTO findById(String id) {
        
    /*2&3*/	//Optional<OwnerDTO> optionalOwnerDTO = ownerRepository.findById(aLong);
   	/*2*/	//return optionalOwnerDTO.get();
    /*3*/	//return optionalOwnerDTO.orElse(null);
    	
    	OwnerMongo owner = ownerRepository.findById(id).orElse(null); 
    	
    	OwnerDTO dto = convertor.convertMongo(owner);
    	dto = addPetsToOwner(dto);
    	
    /*1*/	return dto;
    }

    @Override
    public OwnerDTO save(OwnerDTO ownerDTO) {
    	
    	OwnerMongo owner = ownerRepository.save(convertor.convertMongo(ownerDTO));
    	
        return convertor.convertMongo(owner);
    }

    @Override
    public void delete(OwnerDTO ownerDTO) {
    	
    	OwnerMongo owner = convertor.convertMongo(ownerDTO);
    	
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(String id) {
    	
        ownerRepository.deleteById(id);
    }
    
    private OwnerDTO addPetsToOwner(OwnerDTO ownerDTO) {
    	
    	Set<PetDTO> pets = ownerDTO.getPets();
    	
    	if(!pets.isEmpty()) {
    		
    		ownerDTO.setPets(new HashSet<PetDTO>());
    		
    		for(PetDTO dto : pets) {
    			
    			if(dto.getId() != null) {
    				
    				PetMongo toGet = convertor.convertMongo(dto);
        			toGet = petRepository.findById(toGet.getId()).orElse(null);
        			
        			if(toGet != null) {
        				
        				PetDTO toAdd = convertor.convertMongo(toGet);
        				ownerDTO.addPet(toAdd);
        				
        			}    				
    			}
    		}
    	}
		return ownerDTO;
    	
    }
}
