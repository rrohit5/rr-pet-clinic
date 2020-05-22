package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.OwnerDTO;
import com.petclinic.model.Owner;
import com.petclinic.repository.sdjpa.OwnerRepositorySDJPA;
import com.petclinic.service.OwnerService;

@Service
@Profile("sdjpa")
public class OwnerServiceSDJPA implements OwnerService {

    private final OwnerRepositorySDJPA ownerRepository;
    
    private ModelDTOConvertor convertor;

    public OwnerServiceSDJPA(OwnerRepositorySDJPA ownerRepository
						, ModelDTOConvertor convertor) {
        this.ownerRepository = ownerRepository;
    	this.convertor = convertor;
    }

    @Override
    public OwnerDTO findByLastName(String lastName) {
    	
    	Owner owner = ownerRepository.findByLastName(lastName); 
    	
        return convertor.convert(owner);
    }

    @Override
    public List<OwnerDTO> findAllByLastNameLike(String lastName) {
    	
    	List<Owner> owners = ownerRepository.findAllByLastNameLike(lastName);
    	
    	List<OwnerDTO> ownerDTOList = owners.stream().map((p) -> convertor.convert(p)).collect(Collectors.toList());
    	
        return ownerDTOList;
    }

    @Override
    public List<OwnerDTO> findAll() {
    	
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);
        
        List<OwnerDTO> list = owners.stream().map((p) -> convertor.convert(p))
        		.collect(Collectors.toList()); 
        
        return list;
    }

    @Override
    public OwnerDTO findById(String id) {
        
    /*2&3*/	//Optional<OwnerDTO> optionalOwnerDTO = ownerRepository.findById(aLong);
   	/*2*/	//return optionalOwnerDTO.get();
    /*3*/	//return optionalOwnerDTO.orElse(null);
    	
    	Owner owner = ownerRepository.findById(Long.valueOf(id)).orElse(null); 
    	
    /*1*/	return convertor.convert(owner);
    }

    @Override
    public OwnerDTO save(OwnerDTO ownerDTO) {
    	
    	Owner owner = ownerRepository.save(convertor.convert(ownerDTO));
    	
        return convertor.convert(owner);
    }

    @Override
    public void delete(OwnerDTO ownerDTO) {
    	
    	Owner owner = convertor.convert(ownerDTO);
    	
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(String id) {
    	
        ownerRepository.deleteById(Long.valueOf(id));
    }
}
