package com.petclinic.service.jdbc;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Specialty;
import com.petclinic.service.SpecialtyService;

@Service
@Profile({"jdbc"})
public class SpecialtyServiceImpl implements SpecialtyService {

	@Override
	public List<Specialty> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Specialty findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Specialty save(Specialty object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Specialty object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
