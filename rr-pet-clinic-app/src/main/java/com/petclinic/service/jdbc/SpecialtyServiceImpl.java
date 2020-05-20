package com.petclinic.service.jdbc;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.dto.SpecialtyDTO;
import com.petclinic.service.SpecialtyService;

@Service
@Profile({"jdbc"})
public class SpecialtyServiceImpl implements SpecialtyService {

	@Override
	public List<SpecialtyDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpecialtyDTO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpecialtyDTO save(SpecialtyDTO object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(SpecialtyDTO object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}



}
