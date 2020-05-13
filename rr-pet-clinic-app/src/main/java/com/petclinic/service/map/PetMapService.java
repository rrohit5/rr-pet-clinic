package com.petclinic.service.map;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.petclinic.model.Pet;
import com.petclinic.service.PetService;

@Service
@Profile({"map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService{ 
//implements CrudService<Pet, Long> {

	
	@Override
	public Pet save(Pet object) {
		//remove ID parameter as we are generating it automatically
		return super.save(object);
//		return super.save(object.getId(), object);
	}

	@Override
	public List<Pet> findAll() {
		return super.findAll();
	}

	@Override
	public Pet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Pet object) {
		super.delete(object);
	}

}
