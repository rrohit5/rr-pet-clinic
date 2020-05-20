package com.petclinic.mongo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.SpecialtyDTO;
import com.petclinic.mongo.model.SpecialtyMongo;
import com.petclinic.mongo.repository.SpecialtyRepositoryMongo;
import com.petclinic.service.SpecialtyService;

@Service
@Profile({"mongo"})
public class SpecialtyServiceMongo implements SpecialtyService {

    private final SpecialtyRepositoryMongo specialtyRepository;
    private ModelDTOConvertor convertor;

    public SpecialtyServiceMongo(SpecialtyRepositoryMongo specialtyRepository
    					, ModelDTOConvertor convertor) {
        this.specialtyRepository = specialtyRepository;
        this.convertor = convertor;
    }

    @Override
    public List<SpecialtyDTO> findAll() {
    	
        List<SpecialtyMongo> specialities = new ArrayList<>();
        specialtyRepository.findAll().forEach(specialities::add);
        
        return specialities.stream().map((p) -> convertor.convertMongo(p)).collect(Collectors.toList());
    }

    @Override
    public SpecialtyDTO findById(String id) {
    	
    	SpecialtyMongo sp = specialtyRepository.findById(id).orElse(null);
    	
        return convertor.convertMongo(sp);
    }

    @Override
    public SpecialtyDTO save(SpecialtyDTO specialtyDTO) {
    	
    	SpecialtyMongo sp = specialtyRepository.save(convertor.convertMongo(specialtyDTO));
    			
        return convertor.convertMongo(sp);
    }

    @Override
    public void delete(SpecialtyDTO specialtyDTO) {
    	
        specialtyRepository.delete(convertor.convertMongo(specialtyDTO));
    }

    @Override
    public void deleteById(String id) {
    	
        specialtyRepository.deleteById(id);
    }
}
