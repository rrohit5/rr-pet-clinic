package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.SpecialtyDTO;
import com.petclinic.model.Specialty;
import com.petclinic.repository.sdjpa.SpecialtyRepositorySDJPA;
import com.petclinic.service.SpecialtyService;

@Service
@Profile("sdjpa")
public class SpecialtyServiceSDJPA implements SpecialtyService {

    private final SpecialtyRepositorySDJPA specialtyRepository;
    private ModelDTOConvertor convertor;

    public SpecialtyServiceSDJPA(SpecialtyRepositorySDJPA specialtyRepository
							, ModelDTOConvertor convertor) {
        this.specialtyRepository = specialtyRepository;
        this.convertor = convertor;
    }

    @Override
    public List<SpecialtyDTO> findAll() {
    	
        List<Specialty> specialities = new ArrayList<>();
        specialtyRepository.findAll().forEach(specialities::add);
        
        return specialities.stream().map((p) -> convertor.convert(p)).collect(Collectors.toList());
    }

    @Override
    public SpecialtyDTO findById(String id) {
    	
    	Specialty sp = specialtyRepository.findById(Long.valueOf(id)).orElse(null);
    	
        return convertor.convert(sp);
    }

    @Override
    public SpecialtyDTO save(SpecialtyDTO specialtyDTO) {
    	
    	Specialty sp = specialtyRepository.save(convertor.convert(specialtyDTO));
    			
        return convertor.convert(sp);
    }

    @Override
    public void delete(SpecialtyDTO specialtyDTO) {
    	
        specialtyRepository.delete(convertor.convert(specialtyDTO));
    }

    @Override
    public void deleteById(String id) {
    	
        specialtyRepository.deleteById(Long.valueOf(id));
    }
}
