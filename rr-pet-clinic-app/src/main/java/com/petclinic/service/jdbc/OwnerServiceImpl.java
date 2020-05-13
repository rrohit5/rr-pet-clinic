package com.petclinic.service.jdbc;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petclinic.model.Owner;
import com.petclinic.repository.jdbc.OwnerRepository;
import com.petclinic.service.OwnerService;

@Service
@Profile({"jdbc"})
public class OwnerServiceImpl implements OwnerService {
	
	private OwnerRepository ownerRepository;

	public OwnerServiceImpl(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public List<Owner> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Owner findById(Long id) {

		return ownerRepository.findById(id).get();
	}

	@Override
	@Transactional
	public Owner save(Owner owner) {

		return ownerRepository.save(owner);
	}

	@Override
	public void delete(Owner object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Owner> findAllByLastNameLike(String lastName) {

		return ownerRepository.findAllByLastNameLike(lastName);
	}

	@Override
	public Owner findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
