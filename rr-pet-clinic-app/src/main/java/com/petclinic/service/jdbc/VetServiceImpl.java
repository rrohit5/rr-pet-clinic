package com.petclinic.service.jdbc;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petclinic.model.Vet;
import com.petclinic.repository.jdbc.VetRepository;
import com.petclinic.service.VetService;

@Service
@Profile({"jdbc"})
public class VetServiceImpl implements VetService {
	
	private VetRepository vetRepository;

	public VetServiceImpl(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vet> findAll() {

		return vetRepository.findAll();
	}

	@Override
	public Vet findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vet save(Vet object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Vet object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
