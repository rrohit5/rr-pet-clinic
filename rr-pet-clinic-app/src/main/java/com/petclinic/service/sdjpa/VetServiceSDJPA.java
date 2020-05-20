package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.convertor.ModelDTOConvertor;
import com.petclinic.dto.VetDTO;
import com.petclinic.model.Vet;
import com.petclinic.repository.sdjpa.VetRepositorySDJPA;
import com.petclinic.service.VetService;

@Service
@Profile("sdjpa")
public class VetServiceSDJPA implements VetService {

    private final VetRepositorySDJPA vetRepository;
    private ModelDTOConvertor convertor;

    public VetServiceSDJPA(VetRepositorySDJPA vetRepository
							, ModelDTOConvertor convertor) {
        this.vetRepository = vetRepository;
        this.convertor = convertor;
    }

    @Override
    public List<VetDTO> findAll() {
    	
        List<Vet> vets = new ArrayList<>();
        vetRepository.findAll().forEach(vets::add);
        
        return vets.stream().map((p) -> convertor.convert(p))
        		.collect(Collectors.toList());
    }

    @Override
    public VetDTO findById(String id) {
    	
    	Vet vet = vetRepository.findById(Long.valueOf(id)).orElse(null); 
    	
        return convertor.convert(vet);
    }

    @Override
    public VetDTO save(VetDTO vetDTO) {
    	
    	Vet vet = vetRepository.save(convertor.convert(vetDTO));
    	
        return convertor.convert(vet);
    }

    @Override
    public void delete(VetDTO vetDTO) {
    	
        vetRepository.delete(convertor.convert(vetDTO));
    }

    @Override
    public void deleteById(String id) {
    	
        vetRepository.deleteById(Long.valueOf(id));
    }
}
