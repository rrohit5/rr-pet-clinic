package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.VisitDTO;
import com.petclinic.model.Visit;
import com.petclinic.repository.sdjpa.VisitRepositorySDJPA;
import com.petclinic.service.VisitService;

@Service
@Profile("sdjpa")
public class VisitServiceSDJPA implements VisitService {

    private final VisitRepositorySDJPA visitRepository;
    private ModelDTOConvertor convertor;

    public VisitServiceSDJPA(VisitRepositorySDJPA visitRepository
			, ModelDTOConvertor convertor) {
        this.visitRepository = visitRepository;
        this.convertor = convertor;
    }

    @Override
    public List<VisitDTO> findAll() {
    	
        List<Visit> visits = new ArrayList<>();
        visitRepository.findAll().forEach(visits::add);
        
        return visits.stream().map((p) -> convertor.convert(p))
        		.collect(Collectors.toList());
    }

    @Override
    public VisitDTO findById(String id) {
    	
    	Visit visit = visitRepository.findById(Long.valueOf(id)).orElse(null);
    	
        return convertor.convert(visit); 
    }

    @Override
    public VisitDTO save(VisitDTO visitDTO) {
    	
    	Visit visit = visitRepository.save(convertor.convert(visitDTO)); 
        return convertor.convert(visit);
    }

    @Override
    public void delete(VisitDTO visitDTO) {
    	
        visitRepository.delete(convertor.convert(visitDTO));
    }

    @Override
    public void deleteById(String id) {
    	
        visitRepository.deleteById(Long.valueOf(id));
    }

	@Override
	public List<VisitDTO> findVisitsByPetId(Long petId) {
		// TODO Auto-generated method stub
		return null;
	}
}
