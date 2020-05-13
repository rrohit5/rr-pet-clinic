package com.petclinic.service.jdbc;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petclinic.model.PetType;
import com.petclinic.repository.jdbc.PetTypeRepository;
import com.petclinic.service.PetTypeService;

@Service
@Profile({"jdbc"})
public class PetTypeServiceImpl implements PetTypeService {
	
	private PetTypeRepository petTypeRepository;
	
	
	public PetTypeServiceImpl(PetTypeRepository petTypeRepository) {
		this.petTypeRepository = petTypeRepository;
	}
	
	

	@Override
	@Transactional(readOnly = true)
	public List<PetType> findAll() {

		return petTypeRepository.findPetTypes();
	}

	@Override
	public PetType findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PetType save(PetType petType) {

		return petTypeRepository.save(petType);
	}

	@Override
	public void delete(PetType object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
