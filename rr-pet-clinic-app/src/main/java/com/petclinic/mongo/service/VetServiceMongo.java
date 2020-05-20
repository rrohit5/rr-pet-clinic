package com.petclinic.mongo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.VetDTO;
import com.petclinic.mongo.model.VetMongo;
import com.petclinic.mongo.repository.VetRepositoryMongo;
import com.petclinic.service.VetService;

@Service
@Profile({"mongo"})
public class VetServiceMongo implements VetService {

    private final VetRepositoryMongo vetRepository;
    private ModelDTOConvertor convertor;

    public VetServiceMongo(VetRepositoryMongo vetRepository
    					, ModelDTOConvertor convertor) {
        this.vetRepository = vetRepository;
        this.convertor = convertor;
    }

    @Override
    public List<VetDTO> findAll() {
    	
        List<VetMongo> vets = new ArrayList<>();
        vetRepository.findAll().forEach(vets::add);
        
        return vets.stream().map((p) -> convertor.convertMongo(p))
        		.collect(Collectors.toList());
    }

    @Override
    public VetDTO findById(String id) {
    	
    	VetMongo vet = vetRepository.findById(id).orElse(null); 
    	
        return convertor.convertMongo(vet);
    }

    @Override
    public VetDTO save(VetDTO vetDTO) {
    	
    	VetMongo vet = vetRepository.save(convertor.convertMongo(vetDTO));
    	
        return convertor.convertMongo(vet);
    }

    @Override
    public void delete(VetDTO vetDTO) {
    	
        vetRepository.delete(convertor.convertMongo(vetDTO));
    }

    @Override
    public void deleteById(String id) {
    	
        vetRepository.deleteById(id);
    }
}
