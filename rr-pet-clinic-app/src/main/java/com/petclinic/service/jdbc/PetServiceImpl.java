package com.petclinic.service.jdbc;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petclinic.model.Pet;
import com.petclinic.repository.jdbc.PetRepository;
import com.petclinic.service.PetService;

@Service
@Profile({"jdbc"})
public class PetServiceImpl implements PetService {
	
	private PetRepository petRepository;

	public PetServiceImpl(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public List<Pet> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Pet findById(Long id) {

		return petRepository.findById(id).get();
	}

	@Override
	@Transactional
	public Pet save(Pet pet) {

		return petRepository.save(pet);
	}

	@Override
	public void delete(Pet object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
