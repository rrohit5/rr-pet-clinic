package com.petclinic.service.jdbc;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petclinic.model.Visit;
import com.petclinic.repository.jdbc.VisitRepository;
import com.petclinic.service.VisitService;

@Service
@Profile({"jdbc"})
public class VisitServiceImpl implements VisitService {
	
	private VisitRepository visitRepository;

	public VisitServiceImpl(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public List<Visit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visit findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Visit save(Visit visit) {

		return visitRepository.save(visit);
	}

	@Override
	public void delete(Visit object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Visit> findVisitsByPetId(Long petId) {
		
		return visitRepository.findByPetId(petId);
	}

}
