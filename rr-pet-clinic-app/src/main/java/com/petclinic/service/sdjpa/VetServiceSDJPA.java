package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Vet;
import com.petclinic.repository.sdjpa.VetRepositorySDJPA;
import com.petclinic.service.VetService;

@Service
@Profile("sdjpa")
public class VetServiceSDJPA implements VetService {

    private final VetRepositorySDJPA vetRepository;

    public VetServiceSDJPA(VetRepositorySDJPA vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public List<Vet> findAll() {
        List<Vet> vets = new ArrayList<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        vetRepository.deleteById(aLong);
    }
}
