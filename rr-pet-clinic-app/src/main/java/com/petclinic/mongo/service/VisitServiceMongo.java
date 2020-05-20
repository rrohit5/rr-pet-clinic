package com.petclinic.mongo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.VisitDTO;
import com.petclinic.mongo.model.VisitMongo;
import com.petclinic.mongo.repository.VisitRepositoryMongo;
import com.petclinic.service.VisitService;

@Service
@Profile({"mongo"})
public class VisitServiceMongo implements VisitService {

    private final VisitRepositoryMongo visitRepository;
    private ModelDTOConvertor convertor;

    public VisitServiceMongo(VisitRepositoryMongo visitRepository
    					, ModelDTOConvertor convertor) {
        this.visitRepository = visitRepository;
        this.convertor = convertor;
    }

    @Override
    public List<VisitDTO> findAll() {
    	
        List<VisitMongo> visits = new ArrayList<>();
        visitRepository.findAll().forEach(visits::add);
        
        return visits.stream().map((p) -> convertor.convertMongo(p))
        		.collect(Collectors.toList());
    }

    @Override
    public VisitDTO findById(String id) {
    	
    	VisitMongo visit =  visitRepository.findById(id).orElse(null);
    	
        return convertor.convertMongo(visit); 
    }

    @Override
    public VisitDTO save(VisitDTO visitDTO) {
    	
    	VisitMongo visit = visitRepository.save(convertor.convertMongo(visitDTO)); 
        return convertor.convertMongo(visit);
    }

    @Override
    public void delete(VisitDTO visitDTO) {
    	
        visitRepository.delete(convertor.convertMongo(visitDTO));
    }

    @Override
    public void deleteById(String id) {
    	
        visitRepository.deleteById(id);
    }

	@Override
	public List<VisitDTO> findVisitsByPetId(Long petId) {
		// TODO Auto-generated method stub
		return null;
	}
}
