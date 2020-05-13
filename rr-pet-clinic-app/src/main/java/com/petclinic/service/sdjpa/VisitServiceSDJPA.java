package com.petclinic.service.sdjpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Visit;
import com.petclinic.repository.sdjpa.VisitRepositorySDJPA;
import com.petclinic.service.VisitService;

@Service
@Profile("sdjpa")
public class VisitServiceSDJPA implements VisitService {

    private final VisitRepositorySDJPA visitRepository;

    public VisitServiceSDJPA(VisitRepositorySDJPA visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public List<Visit> findAll() {
        List<Visit> visits = new ArrayList<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }

	@Override
	public List<Visit> findVisitsByPetId(Long petId) {
		// TODO Auto-generated method stub
		return null;
	}
}
