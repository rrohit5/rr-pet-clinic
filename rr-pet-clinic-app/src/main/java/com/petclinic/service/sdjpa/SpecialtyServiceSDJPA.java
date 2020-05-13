package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Specialty;
import com.petclinic.repository.sdjpa.SpecialtyRepositorySDJPA;
import com.petclinic.service.SpecialtyService;

@Service
@Profile("sdjpa")
public class SpecialtyServiceSDJPA implements SpecialtyService {

    private final SpecialtyRepositorySDJPA specialtyRepository;

    public SpecialtyServiceSDJPA(SpecialtyRepositorySDJPA specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public List<Specialty> findAll() {
        List<Specialty> specialities = new ArrayList<>();
        specialtyRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Specialty findById(Long aLong) {
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialtyRepository.deleteById(aLong);
    }
}
